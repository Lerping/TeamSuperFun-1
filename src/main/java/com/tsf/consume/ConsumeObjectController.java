package com.tsf.consume;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.hateoas.EntityModel;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class ConsumeObjectController {

    private final ConsumeObjectModelAssembler assembler;

    ConsumeObjectController(ConsumeObjectModelAssembler assembler){
        this.assembler = assembler;
    }

    @GetMapping("/consume")
    EntityModel<ConsumeObject> getConsume() {
        final String URI = "https://quoters.apps.pcfone.io/api/random";

        RestTemplate restTemplate = new RestTemplate();
        ConsumeObject consumeObject = restTemplate.getForObject(URI, ConsumeObject.class)
                ;//.orElseThrow(() -> new ConsumeObjectNotFoundException());

        return assembler.toModel(consumeObject);
    }
}
