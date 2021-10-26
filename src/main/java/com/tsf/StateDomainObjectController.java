package com.tsf;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import org.springframework.http.*;
import org.springframework.hateoas.*;
import org.springframework.hateoas.mediatype.problem.Problem;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.methodOn;

@RestController
class StateDomainObjectController {

    private final StateDomainObjectRepository stateDomainObjectRepository;
    private final StateDomainObjectModelAssembler assembler;

    StateDomainObjectController(StateDomainObjectRepository stateDomainObjectRepository, StateDomainObjectModelAssembler assembler) {
        this.stateDomainObjectRepository = stateDomainObjectRepository;
        this.assembler = assembler;
    }

    @GetMapping("/stateDomainObjects")
    CollectionModel<EntityModel<StateDomainObject>> all() {

        List<EntityModel<StateDomainObject>> stateDomainObjects =
            stateDomainObjectRepository.findAll()
            .stream() //
            .map(assembler::toModel) //
            .collect(Collectors.toList());

        return CollectionModel.of(stateDomainObjects, //
            linkTo(methodOn(StateDomainObjectController.class).all())
                .withSelfRel());
    }

    @GetMapping("/stateDomainObjects/{id}")
    EntityModel<StateDomainObject> one(@PathVariable Long id) {

        StateDomainObject stateDomainObject = stateDomainObjectRepository.findById(id) //
            .orElseThrow(() -> new StateDomainObjectNotFoundException(id));

        return assembler.toModel(stateDomainObject);
    }

    @DeleteMapping("/stateDomainObjects/{id}/kill")
    ResponseEntity<?> kill(@PathVariable Long id) {

        StateDomainObject stateDomainObject = stateDomainObjectRepository.findById(id) //
            .orElseThrow(() -> new StateDomainObjectNotFoundException(id));

        if (stateDomainObject.getState() == State.START) {
            stateDomainObject.setState(State.KILL);
            return ResponseEntity.ok(assembler.toModel(stateDomainObjectRepository.save(stateDomainObject)));
        }

        return ResponseEntity //
            .status(HttpStatus.METHOD_NOT_ALLOWED) //
            .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
            .body(Problem.create() //
            .withTitle("Method not allowed") //
            .withDetail("You can't cancel an order that is in the " + stateDomainObject.getState() + " state"));
    }

    @PutMapping("/stateDomainObjects/{id}/finish")
    ResponseEntity<?> finish(@PathVariable Long id) {

        StateDomainObject stateDomainObject = stateDomainObjectRepository.findById(id) //
        .orElseThrow(() -> new StateDomainObjectNotFoundException(id));

        if (stateDomainObject.getState() == State.START) {
            stateDomainObject.setState(State.FINISH);
            return ResponseEntity.ok(assembler.toModel(stateDomainObjectRepository.save(stateDomainObject)));
        }

        return ResponseEntity //
        .status(HttpStatus.METHOD_NOT_ALLOWED) //
        .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
        .body(Problem.create() //
        .withTitle("Method not allowed") //
        .withDetail("You can't complete an order that is in the " + stateDomainObject.getState() + " state"));
    }

    @PostMapping("/stateDomainObjects")
    ResponseEntity<EntityModel<StateDomainObject>> newStateDomainObject(@RequestBody StateDomainObject stateDomainObject) {

        stateDomainObject.setState(State.START);
        StateDomainObject newStateDomainObject = stateDomainObjectRepository.save(stateDomainObject);

        return ResponseEntity //
            .created(linkTo(methodOn(StateDomainObjectController.class)
            .one(newStateDomainObject.getId())).toUri())
            .body(assembler.toModel(newStateDomainObject));
    }
}
