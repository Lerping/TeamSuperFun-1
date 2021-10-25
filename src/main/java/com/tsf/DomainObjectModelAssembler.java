package com.tsf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class DomainObjectModelAssembler implements RepresentationModelAssembler<DomainObject, EntityModel<DomainObject>> {

    @Override
    public EntityModel<DomainObject> toModel(DomainObject domainObject) {
        return EntityModel.of(domainObject, //
            linkTo(methodOn(DomainObjectController.class).one(domainObject.getId())).withSelfRel(),
            linkTo(methodOn(DomainObjectController.class).all()).withRel("domainObjects"));
    }
}
