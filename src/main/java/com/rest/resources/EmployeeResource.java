package com.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.entity.Employee;
import org.springframework.hateoas.ResourceSupport;


public class EmployeeResource extends ResourceSupport {

    private Employee employee;

    @JsonCreator
    public EmployeeResource(@JsonProperty("employee") Employee employee) {
        this.employee = employee;
    }

    public Employee getEmployee() {
        return employee;
    }

    public void setEmployee(Employee employee) {
        this.employee = employee;
    }
}
