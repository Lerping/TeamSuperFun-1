package com.tsf.legacy.state;

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
public class StateDomainObjectController {

    private final StateDomainObjectRepository repository;
    private final StateDomainObjectModelAssembler assembler;

    StateDomainObjectController(StateDomainObjectRepository repository, StateDomainObjectModelAssembler assembler) {
        this.repository = repository;
        this.assembler = assembler;
    }

    @GetMapping("/state")
    CollectionModel<EntityModel<StateDomainObject>> getStateDomainObjects() {

        List<EntityModel<StateDomainObject>> stateDomainObjects = repository.findAll().stream() //
                .map(assembler::toModel) //
                .collect(Collectors.toList());

        return CollectionModel.of(stateDomainObjects, //
                linkTo(methodOn(StateDomainObjectController.class).getStateDomainObjects()).withSelfRel());
    }

    @GetMapping("/state/{id}")
    EntityModel<StateDomainObject> getStateDomainObject(@PathVariable Long id) {

        StateDomainObject stateDomainObject = repository.findById(id) //
                .orElseThrow(() -> new StateDomainObjectNotFoundException(id));

        return assembler.toModel(stateDomainObject);
    }

    @PutMapping("/state/{id}/kill")
    ResponseEntity<?> killStateDomainObject(@PathVariable Long id) {

        StateDomainObject stateDomainObject = repository.findById(id) //
                .orElseThrow(() -> new StateDomainObjectNotFoundException(id));

        if (stateDomainObject.getState() == State.START) {
            stateDomainObject.setState(State.KILL);
            return ResponseEntity.ok(assembler.toModel(repository.save(stateDomainObject)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Not allowed") //
                        .withDetail("KILL not allowed on: " + stateDomainObject.getState()));
    }

    @PutMapping("/state/{id}/finish")
    ResponseEntity<?> finishStateDomainObject(@PathVariable Long id) {

        StateDomainObject stateDomainObject = repository.findById(id) //
                .orElseThrow(() -> new StateDomainObjectNotFoundException(id));

        if (stateDomainObject.getState() == State.START) {
            stateDomainObject.setState(State.FINISH);
            return ResponseEntity.ok(assembler.toModel(repository.save(stateDomainObject)));
        }

        return ResponseEntity //
                .status(HttpStatus.METHOD_NOT_ALLOWED) //
                .header(HttpHeaders.CONTENT_TYPE, MediaTypes.HTTP_PROBLEM_DETAILS_JSON_VALUE) //
                .body(Problem.create() //
                        .withTitle("Not allowed") //
                        .withDetail("FINISH not allowed on: " + stateDomainObject.getState() + " state"));
    }

    @PutMapping("/state/start")
    ResponseEntity<EntityModel<StateDomainObject>> startStateDomainObject(
            @RequestBody StateDomainObject stateDomainObject) {

        stateDomainObject.setState(State.START);
        StateDomainObject newStateDomainObject = repository.save(stateDomainObject);

        return ResponseEntity //
                .created(linkTo(
                        methodOn(StateDomainObjectController.class).getStateDomainObject(newStateDomainObject.getId()))
                                .toUri())
                .body(assembler.toModel(newStateDomainObject));
    }

    @DeleteMapping("/state/{id}")
    ResponseEntity<?> deleteStateDomainObject(@PathVariable Long id) {
        repository.deleteById(id);
        return ResponseEntity.noContent().build();
    }
}
