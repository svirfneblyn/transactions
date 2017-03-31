package com.rest.repositoryservices;

import com.rest.baseoperations.BaseRestOperations;
import com.rest.entity.Department;
import com.rest.entity.repository.DepartmentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Service
public class DepartmentService implements BaseRestOperations {
    @Autowired
    private DepartmentRepository repository;

    public List<Department> getAll() {
        Iterator<Department> alldeptlIterator = repository.findAll().iterator();
        List<Department> deptList = new ArrayList<>();
        alldeptlIterator.forEachRemaining(deptList::add);
        return deptList;
    }

    public ResponseEntity update(@RequestBody Object entity) {
        repository.save((Department) entity);
        return new ResponseEntity<Department>((Department) entity, HttpStatus.OK);
    }


    public ResponseEntity<Department> add(@RequestBody Object entity) {
        repository.save((Department) entity);
        return new ResponseEntity<Department>((Department) entity, HttpStatus.CREATED);
    }

    public Department get(@PathVariable("id") Long id) {
        return repository.findOne(id);
    }

    public ResponseEntity<Department> getById(@PathVariable("id") Long id) {
        if (repository.findOne(id) == null) {
            return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Department>(repository.findOne(id), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Department> delete(@PathVariable("id") Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return new ResponseEntity<Department>(HttpStatus.OK);
        }
        return new ResponseEntity<Department>(HttpStatus.NOT_FOUND);
    }
}
