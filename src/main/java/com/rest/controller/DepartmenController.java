package com.rest.controller;

import com.rest.entity.Department;
import com.rest.repositoryservices.DepartmentService;
import com.rest.resources.DepartmentResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/department")
public class DepartmenController {
    @Autowired
    private DepartmentService service;
   /* @Autowired
   private DepartmentResourceAssembler departmentResourceAssembler;*/
    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Department> getAll() {
        return service.getAll();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@RequestBody Department entity) {
        service.add(entity);

        return new ResponseEntity<Department>(entity, HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Department> add(@RequestBody Department entity) {
        service.update(entity);
        return new ResponseEntity<Department>(entity, HttpStatus.CREATED);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<DepartmentResource> getById(@PathVariable("id") Long id) {
        Department department = (service.get(id));
        DepartmentResource dr = new DepartmentResource(department);
        dr.add(linkTo(methodOn(DepartmenController.class).getById(id)).withRel("getById"));
        return ResponseEntity.ok(dr);
    }
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<Department> delete(@PathVariable("id") Long id) {
        return service.delete(id);
    }
}
