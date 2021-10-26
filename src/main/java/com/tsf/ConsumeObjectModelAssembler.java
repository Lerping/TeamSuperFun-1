/* Convert a non-model object (BasicDomainObject) into a model based object (EntityModel<BasicDomainObject>)
 *
 * Acts as the view for the BasicDomainObject
 */

package com.tsf;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.RepresentationModelAssembler;
import org.springframework.stereotype.Component;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

@Component
class ConsumeObjectModelAssembler
        implements RepresentationModelAssembler<ConsumeObject, EntityModel<ConsumeObject>> {

    @Override
    public EntityModel<ConsumeObject> toModel(ConsumeObject consumeObject) {
        return EntityModel.of(consumeObject);
        /*
        return EntityModel.of(consumeObject,
                linkTo(methodOn(ConsumeController.class).getBasicDomainObject(consumeObject.getId()))
                        .withSelfRel(),
                linkTo(methodOn(ConsumeController.class).getBasicDomainObjects())
                        .withRel("basic"));
        */
    }
}
