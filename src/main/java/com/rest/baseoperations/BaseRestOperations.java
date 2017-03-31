package com.rest.baseoperations;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

public interface BaseRestOperations {
    List<?> getAll();
    ResponseEntity update(@RequestBody Object obj) ;
    ResponseEntity<?> add(@RequestBody Object obj);
    ResponseEntity<?> getById(@PathVariable("id") Long id);
    ResponseEntity<?> delete(@PathVariable("id") Long id);
}
