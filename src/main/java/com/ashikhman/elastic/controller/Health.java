package com.ashikhman.elastic.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Health {

    @GetMapping(value = "/admin/health", produces = "application/json")
    public ResponseEntity<?> health() {
        return new ResponseEntity<>("{\"status\":\"UP\"}", HttpStatus.OK);
    }

    @GetMapping(value = "loans/loadPersons", produces = "application/json")
    public ResponseEntity<?> load() {
        return new ResponseEntity<>("{\"status\":\"UP\"}", HttpStatus.OK);
    }
}
