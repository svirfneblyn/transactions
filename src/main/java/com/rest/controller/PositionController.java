package com.rest.controller;

import com.rest.entity.Position;
import com.rest.repositoryservices.PositionsService;
import com.rest.resources.PositionResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/position")
public class PositionController {
    @Autowired
    private PositionsService service;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Position> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@RequestBody Position entity) {
        service.add(entity);
        return new ResponseEntity<Position>(entity, HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Position> add(@RequestBody Position entity) {
        service.update(entity);
        return new ResponseEntity<Position>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Position> getById(@PathVariable("id") Long id) {
        Position position = service.get(id);
        PositionResource pr = new PositionResource(position);
        pr.add(linkTo(methodOn(EmployeeController.class).getById(id)).withRel("get"));
        return service.getById(id);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Position> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
