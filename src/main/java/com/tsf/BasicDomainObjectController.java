/* Business logic for the /BasicDomainObjects endpoint */


/* Overview of Spring HATEOAS (Hypermedia as the Engine of Application State):
*/
package com.tsf;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import org.springframework.http.ResponseEntity;

import org.springframework.hateoas.IanaLinkRelations;
import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.CollectionModel;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

/* @RestController
*   Indicates that the data returned by each method will be written straight
*   into the response body instead of rendering a template
*/
@RestController
class BasicDomainObjectController {

    private final BasicDomainObjectRepository repository;
    private final BasicDomainObjectModelAssembler assembler;

    BasicDomainObjectController(BasicDomainObjectRepository repository,
        BasicDomainObjectModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

//    /* RPC - NOT RESTful
//     * HTTP GET - Get all BasicDomainObjects
//     */
//    @GetMapping("/BasicDomainObjects")
//    List<BasicDomainObject> getBasicDomainObjects() {
//        return repository.findAll();
//    }

     /* RESTful version of above
      * HTTP GET - Get all BasicDomainObjects
      *
      * Differences from above RPC:
      *   Return type of CollectionModel<>, is a Spring HATEOAS container used
      *   to encapsulate collections of resources. Like EntityModel<> but for
      *   collections.
      *
      *   CollectionModel<> acts as the view in MVC
      */
    @GetMapping("/basicDomainObjects")
    CollectionModel<EntityModel<BasicDomainObject>> getBasicDomainObjects() {
        List<EntityModel<BasicDomainObject>> basicDomainObjects = repository.findAll()
            .stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(basicDomainObjects,
            linkTo(methodOn(BasicDomainObjectController.class).getBasicDomainObjects()).withSelfRel());
    }

//    /* RPC - NOT RESTful
//     * HTTP POST - Create a new BasicDomainObject
//     */
//    @PostMapping("/BasicDomainObjects")
//    BasicDomainObject newBasicDomainObject(@RequestBody BasicDomainObject newBasicDomainObject) {
//        return repository.save(newBasicDomainObject);
//    }

    /* RESTful version of above
     * HTTP POST - Create a new BasicDomainObject
     *
     * Differences from above RPC:
     *   BasicDomainObject is saved as above, but the object is wrapped using the
     *   BasicDomainObjectModelAssembler
     *
     *   ResponseEntity<> is used to create an HTTP 201 Created status message,
     *   including a Location response header
     */
    @PostMapping("/basicDomainObjects")
    ResponseEntity<?> postBasicDomainObject(@RequestBody BasicDomainObject basicDomainObject) {
        EntityModel<BasicDomainObject> entityModel = assembler.toModel(
            repository.save(basicDomainObject));

        return ResponseEntity.created(entityModel.getRequiredLink(
            IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

//    /* RPC - NOT RESTful
//     * HTTP GET - Get the BasicDomainObject identified by {id}
//     */
//    @GetMapping("/BasicDomainObject/{id}")
//    BasicDomainObject one(@PathVariable Long id) {
//
//    return repository.findById(id)
//    .orElseThrow(() -> new BasicDomainObjectNotFoundException(id));
//    }


    /* RESTful version of above
     * HTTP GET - Get the BasicDomainObject identified by {id}
     *
     * Differences from above RPC:
     *   Return type of the method is EntityMode<BasicDomainObject>, a generic
     *   container from Spring HATEOAS that includes the data AND a collection
     *   of links
     *
     *   linkTo(methodOn(EmployeeController.class).one(id) asks that Spring
     *   HATEOAS build a link to the BasicDomainObjectController.one() and flag it
     *   as a self link
     *
     *   linkTo(methodOn(EmployeeController.class).getBasicDomainObjects() asks Spring HATEOAS to
     *   build a link to the aggregate root, getBasicDomainObjects(), and call it "BasicDomainObjects"
     */
    @GetMapping("/basicDomainObjects/{id}")
    EntityModel<BasicDomainObject> getBasicDomainObject(@PathVariable Long id) {
        BasicDomainObject basicDomainObject = repository.findById(id)
            .orElseThrow(() -> new BasicDomainObjectNotFoundException(id));

        return assembler.toModel(basicDomainObject);
    }

//    /* RPC - NOT RESTful
//     * HTTP PUT - Update the BasicDomainObject identified by {id}
//     */
//    @PutMapping("/BasicDomainObjects/{id}")
//    BasicDomainObject replaceBasicDomainObject(@RequestBody BasicDomainObject newBasicDomainObject, @PathVariable Long id) {
//        return repository.findById(id)
//        .map(BasicDomainObject -> {
//            BasicDomainObject.setData(newBasicDomainObject.getData());
//            return repository.save(BasicDomainObject);
//        })
//        .orElseGet(() -> {
//            newBasicDomainObject.setId(id);
//            return repository.save(newBasicDomainObject);
//        });
//    }

    /* RESTful version of above
     * HTTP PUT - Update the BasicDomainObject identified by {id}
     *
     * Differences from above RPC:
     *   BasicDomainObject object built fomr teh save() operation is wrapped using
     *   the BasicDomainObjectModelAssembler into an EntityModel<BasicDomainObject> object.
     *   Using the getRequiredLink() method, you can retrieve the Link created by
     *   BasicDomainObjectModelAssembler with a SELF rel.
     *
     *   ResponseEntity<> will provide more detail than a 200 OK HTTP response
     *   code. The method created() is used to plug in the resources URI. The
     *   default Location response code HTTP 201 Created is used.
     *
     */
    @PutMapping("/basicDomainObjects/{id}")
    ResponseEntity<?> putBasicDomainObject(@RequestBody BasicDomainObject basicDomainObject, @PathVariable Long id) {
        BasicDomainObject updatedBasicDomainObject = repository.findById(id)
            .map(BasicDomainObject -> {
                BasicDomainObject.setData(newBasicDomainObject.getData());
                return repository.save(BasicDomainObject);
            })
            .orElseGet(() -> {
                newBasicDomainObject.setId(id);
                return repository.save(newBasicDomainObject);
            });

        EntityModel<BasicDomainObject> entityModel = assembler.toModel(updatedBasicDomainObject);

        return ResponseEntity.created(entityModel.getRequiredLink(
            IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /* HTTP DELETE - Delete the BasicDomainObject identified by {id} */
    @DeleteMapping("/basicDomainObjects/{id}")
    ResponseEntity<?> deleteBasicDomainObject(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
