package com.rest.resources;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.rest.entity.Department;
import org.springframework.hateoas.ResourceSupport;

public class DepartmentResource extends ResourceSupport {

    private Department department;

    @JsonCreator
    public DepartmentResource(@JsonProperty("department") Department department) {
        this.department = department;
    }

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }
}
