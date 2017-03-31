package com.rest.controller;

import com.rest.entity.Employee;
import com.rest.repositoryservices.EmployeeService;
import com.rest.resources.EmployeeResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.hateoas.mvc.ControllerLinkBuilder.linkTo;
import static org.springframework.hateoas.mvc.ControllerLinkBuilder.methodOn;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService emplService;

    @RequestMapping(method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public List<Employee> getAll() {
        return emplService.getAllEmployees();
    }

    @RequestMapping(method = RequestMethod.POST)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity update(@RequestBody Employee employee) {
        emplService.save(employee);
        return new ResponseEntity<Employee>(employee, HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.PUT)
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<Employee> add(@RequestBody Employee emp) {
        if (emplService.save(emp)) {
            return new ResponseEntity<>(emp, HttpStatus.OK);
        }
        return new ResponseEntity<>(emp, HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseStatus(HttpStatus.FOUND)
    public ResponseEntity<Employee> getById(@PathVariable("id") Long id) {
        Employee employee = emplService.getEmployeById(id);
        if (employee == null) {

            return new ResponseEntity<Employee>(HttpStatus.NOT_FOUND);
        }
        EmployeeResource er = new EmployeeResource(employee);
        er.add(linkTo(methodOn(EmployeeController.class).getById(id)).withRel("getById"));
        return new ResponseEntity<Employee>(employee, HttpStatus.OK);
    }

    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseStatus(HttpStatus.OK)
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (emplService.delete(id)) {
            return new ResponseEntity<Void>(HttpStatus.GONE);
        }
        return new ResponseEntity<Void>(HttpStatus.NOT_FOUND);

    }
 /*   private EmployeeResource employeeToResource(Employee employee){
        return (EmployeeResource) employeeResourceAssembler.toResource(employee);
    }*/
}
