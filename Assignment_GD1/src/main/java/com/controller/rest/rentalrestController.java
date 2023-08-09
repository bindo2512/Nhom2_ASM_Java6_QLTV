package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.Retail;
import com.fasterxml.jackson.databind.JsonNode;
import com.service.rentalService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/rental")
public class rentalrestController {
    @Autowired
    rentalService service;

    @PostMapping
    public Retail create(@RequestBody JsonNode orderData) {
        return service.create(orderData);
    }

    @GetMapping()
    public List<Retail> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public Retail update(@PathVariable("id") String id, @RequestBody Retail retail) {
        return service.update(retail);
    }
}
