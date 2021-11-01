/* Convert a non-model object (BasicDomainObject) into a model based object (EntityModel<BasicDomainObject>)
 *
 * Acts as the view for the BasicDomainObject
 */

package com.tsf.legacy.basic;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class BasicDomainObjectModelAssembler
        implements RepresentationModelAssembler<BasicDomainObject, EntityModel<BasicDomainObject>> {

    @Override
    public EntityModel<BasicDomainObject> toModel(BasicDomainObject basicDomainObject) {
        return EntityModel.of(basicDomainObject,
                linkTo(methodOn(BasicDomainObjectController.class).getBasicDomainObject(basicDomainObject.getId()))
                        .withSelfRel(),
                linkTo(methodOn(BasicDomainObjectController.class).getBasicDomainObjects())
                        .withRel("basic"));
    }
}
