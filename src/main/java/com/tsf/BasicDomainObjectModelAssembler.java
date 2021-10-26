/* Convert a non-model object (BasicDomainObject) into a model based object (EntityModel<BasicDomainObject>) */

package com.tsf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class BasicDomainObjectModelAssembler implements RepresentationModelAssembler<BasicDomainObject, EntityModel<BasicDomainObject>> {

    @Override
    public EntityModel<BasicDomainObject> toModel(BasicDomainObject BasicDomainObject) {
        return EntityModel.of(BasicDomainObject, //
            linkTo(methodOn(BasicDomainObjectController.class).one(BasicDomainObject.getId())).withSelfRel(),
            linkTo(methodOn(BasicDomainObjectController.class).all()).withRel("basicDomainObjects"));
    }
}
