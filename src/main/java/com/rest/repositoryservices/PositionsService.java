package com.rest.repositoryservices;

import com.rest.baseoperations.BaseRestOperations;
import com.rest.entity.Position;
import com.rest.entity.repository.PositionRepository;
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
public class PositionsService implements BaseRestOperations {
    @Autowired
    private PositionRepository repository;

    public List<Position> getAll() {
        Iterator<Position> allPositlIterator = repository.findAll().iterator();
        List<Position> positList = new ArrayList<>();
        allPositlIterator.forEachRemaining(positList::add);
        return positList;
    }

    public ResponseEntity update(@RequestBody Object entity) {
        repository.save((Position) entity);
        return new ResponseEntity<Position>((Position) entity, HttpStatus.OK);
    }

    public ResponseEntity<Position> add(@RequestBody Object entity) {
        repository.save((Position) entity);
        return new ResponseEntity<Position>((Position) entity, HttpStatus.CREATED);
    }

    public Position get(Long id) {
        return repository.findOne(id);
    }

    public ResponseEntity<Position> getById(@PathVariable("id") Long id) {
        if (repository.findOne(id) == null) {
            return new ResponseEntity<Position>(HttpStatus.NOT_FOUND);
        }
        return new ResponseEntity<Position>(repository.findOne(id), HttpStatus.FOUND);
    }

    @Override
    public ResponseEntity<Position> delete(@PathVariable("id") Long id) {
        if (repository.exists(id)) {
            repository.delete(id);
            return new ResponseEntity<Position>(HttpStatus.OK);
        }
        return new ResponseEntity<Position>(HttpStatus.NOT_FOUND);
    }
}
