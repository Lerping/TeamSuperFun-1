package com.tsf;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

@Component
class StateDomainObjectModelAssembler implements RepresentationModelAssembler<StateDomainObject, EntityModel<StateDomainObject>> {

    @Override
    public EntityModel<StateDomainObject> toModel(StateDomainObject stateDomainObject) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<StateDomainObject> stateDomainObjectModel = EntityModel.of(stateDomainObject,
        linkTo(methodOn(StateDomainObjectController.class).one(stateDomainObject.getId())).withSelfRel(),
        linkTo(methodOn(StateDomainObjectController.class).all()).withRel("stateDomainObjects"));

        // Conditional links based on state of the stateDomainObject

        if (stateDomainObject.getState() == State.START) {
            stateDomainObjectModel.add(linkTo(methodOn(StateDomainObjectController.class).kill(stateDomainObject.getId())).withRel("KILL"));
            stateDomainObjectModel.add(linkTo(methodOn(StateDomainObjectController.class).finish(stateDomainObject.getId())).withRel("FINISH"));
        }

        return stateDomainObjectModel;
    }
}
