/* Business logic for the /domainObjects endpoint */


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
class DomainObjectController {

    private final DomainObjectRepository repository;
    private final DomainObjectModelAssembler assembler;

    DomainObjectController(DomainObjectRepository repository,
        DomainObjectModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

//    /* RPC - NOT RESTful
//     * HTTP GET - Get all DomainObjects
//     */
//    @GetMapping("/domainObjects")
//    List<DomainObject> all() {
//        return repository.findAll();
//    }

     /* RESTful version of above
      * HTTP GET - Get all DomainObjects
      *
      * Differences from above RPC:
      *   Return type of CollectionModel<>, is a Spring HATEOAS container used
      *   to encapsulate collections of resources. Like EntityModel<> but for
      *   collections.
      */
    @GetMapping("/domainObjects")
    CollectionModel<EntityModel<DomainObject>> all() {
        List<EntityModel<DomainObject>> domainObjects = repository.findAll()
            .stream().map(assembler::toModel).collect(Collectors.toList());

        return CollectionModel.of(domainObjects,
            linkTo(methodOn(DomainObjectController.class).all()).withSelfRel());
    }

//    /* RPC - NOT RESTful
//     * HTTP POST - Create a new DomainObject
//     */
//    @PostMapping("/domainObjects")
//    DomainObject newDomainObject(@RequestBody DomainObject newDomainObject) {
//        return repository.save(newDomainObject);
//    }

    /* RESTful version of above
     * HTTP POST - Create a new DomainObject
     *
     * Differences from above RPC:
     *   DomainObject is saved as above, but the object is wrapped using the
     *   DomainObjectModelAssembler
     *
     *   ResponseEntity<> is used to create an HTTP 201 Created status message,
     *   including a Location response header
     *
     *   Return the model based version of the saved object
     */
    @PostMapping("/domainObjects")
    ResponseEntity<?> newDomainObject(@RequestBody DomainObject newDomainObject) {
        EntityModel<DomainObject> entityModel = assembler.toModel(
            repository.save(newDomainObject));

        return ResponseEntity.created(entityModel.getRequiredLink(
            IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

//    /* RPC - NOT RESTful
//     * HTTP GET - Get the DomainObject identified by {id}
//     */
//    @GetMapping("/domainObject/{id}")
//    DomainObject one(@PathVariable Long id) {
//
//    return repository.findById(id)
//    .orElseThrow(() -> new DomainObjectNotFoundException(id));
//    }


    /* RESTful version of above
     * HTTP GET - Get the DomainObject identified by {id}
     *
     * Differences from above RPC:
     *   Return type of the method is EntityMode<DomainObject>, a generic
     *   container from Spring HATEOAS that includes the data AND a collection
     *   of links
     *
     *   linkTo(methodOn(EmployeeController.class).one(id) asks that Spring
     *   HATEOAS build a link to the DomainObjectController.one() and flag it
     *   as a self link
     *
     *   linkTo(methodOn(EmployeeController.class).all() asks Spring HATEOAS to
     *   build a link to the aggregate root, all(), and call it "domainObjects"
     */
    @GetMapping("/domainObjects/{id}")
    EntityModel<DomainObject> one(@PathVariable Long id) {
        DomainObject domainObject = repository.findById(id)
            .orElseThrow(() -> new DomainObjectNotFoundException(id));

        return assembler.toModel(domainObject);
    }

//    /* RPC - NOT RESTful
//     * HTTP PUT - Update the DomainObject identified by {id}
//     */
//    @PutMapping("/domainObjects/{id}")
//    DomainObject replaceDomainObject(@RequestBody DomainObject newDomainObject, @PathVariable Long id) {
//        return repository.findById(id)
//        .map(domainObject -> {
//            domainObject.setData(newDomainObject.getData());
//            return repository.save(domainObject);
//        })
//        .orElseGet(() -> {
//            newDomainObject.setId(id);
//            return repository.save(newDomainObject);
//        });
//    }

    /* RESTful version of above
     * HTTP PUT - Update the DomainObject identified by {id}
     *
     * Differences from above RPC:
     *   DomainObject object built fomr teh save() operation is wrapped using
     *   the DomainObjectModelAssembler into an EntityModel<DomainObject> object.
     *   Using the getRequiredLink() method, you can retrieve the Link created by
     *   DomainObjectModelAssembler with a SELF rel.
     *
     *   ResponseEntity<> will provide more detail than a 200 OK HTTP response
     *   code. The method created() is used to plug in the resources URI. The
     *   default Location response code HTTP 201 Created is used.
     *
     */
    @PutMapping("/domainObjects/{id}")
    ResponseEntity<?> replaceDomainObject(@RequestBody DomainObject newDomainObject, @PathVariable Long id) {
        DomainObject updatedDomainObject = repository.findById(id)
            .map(domainObject -> {
                domainObject.setData(newDomainObject.getData());
                return repository.save(domainObject);
            })
            .orElseGet(() -> {
                newDomainObject.setId(id);
                return repository.save(newDomainObject);
            });

        EntityModel<DomainObject> entityModel = assembler.toModel(updatedDomainObject);

        return ResponseEntity.created(entityModel.getRequiredLink(
            IanaLinkRelations.SELF).toUri()).body(entityModel);
    }

    /* HTTP DELETE - Delete the DomainObject identified by {id} */
    @DeleteMapping("/domainObjects/{id}")
    void deleteDomainObject(@PathVariable Long id) {
        repository.deleteById(id);
    }
}
