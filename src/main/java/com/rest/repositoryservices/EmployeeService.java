package com.rest.repositoryservices;

import com.rest.entity.Employee;
import com.rest.entity.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
@Service
public class EmployeeService {
    @Autowired
    EmployeeRepository employeeRepository;

    public Employee getEmployeById(Long id) {

        return employeeRepository.findOne(id);
    }

    public List<Employee> getAllEmployees() {
        Iterator<Employee> allemplIterator = employeeRepository.findAll().iterator();
        List<Employee> emplList = new ArrayList<>();
        allemplIterator.forEachRemaining(emplList::add);
        return emplList;
    }

    public Boolean save(Employee empl) {
        employeeRepository.save(empl);
        return true;
    }

    public Boolean delete(Long id) {
        if (employeeRepository.exists(id)) {
            employeeRepository.delete(id);
            return true;
        }
        return false;
    }

}
