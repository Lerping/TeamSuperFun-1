package com.tsf;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class StateDomainObjectModelAssembler
        implements RepresentationModelAssembler<StateDomainObject, EntityModel<StateDomainObject>> {

    @Override
    public EntityModel<StateDomainObject> toModel(StateDomainObject stateDomainObject) {

        // Unconditional links to single-item resource and aggregate root

        EntityModel<StateDomainObject> stateDomainObjectModel = EntityModel.of(stateDomainObject,
                linkTo(methodOn(StateDomainObjectController.class).getStateDomainObject(stateDomainObject.getId()))
                        .withSelfRel(),
                linkTo(methodOn(StateDomainObjectController.class).getStateDomainObjects())
                        .withRel("state"));

        // Conditional links based on state of the stateDomainObject

        if (stateDomainObject.getState() == State.START) {
            stateDomainObjectModel.add(
                    linkTo(methodOn(StateDomainObjectController.class).killStateDomainObject(stateDomainObject.getId()))
                            .withRel("START"));
            stateDomainObjectModel.add(linkTo(
                    methodOn(StateDomainObjectController.class).finishStateDomainObject(stateDomainObject.getId()))
                            .withRel("FINISH"));
        }

        return stateDomainObjectModel;
    }
}
