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

import com.entity.details;
import com.entity.retails;
import com.fasterxml.jackson.databind.JsonNode;
import com.service.rentalService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/rental")
public class rentalrestController {
    @Autowired
    rentalService service;

    @PostMapping
    public retails create(@RequestBody JsonNode orderData) {
        return service.create(orderData);
    }

    @GetMapping()
    public List<retails> getAll() {
        return service.findAll();
    }

    @PutMapping("/{id}")
    public retails update(@PathVariable("id") String id, @RequestBody retails retail) {
        return service.updateSRetails(retail);
    }

    @GetMapping("/username/{username}")
    public List<retails> getByUsername(@PathVariable("username") String username) {
        return service.findByAccountUsername(username);
    }

    @GetMapping("/id/{id}")
    public List<details> getByRetailId(@PathVariable("id") Integer id) {
        return service.findById(id);
    }

    @GetMapping("/details/all")
    public List<details> getAllRDetails() {
        return service.findAllRDetails();
    }
}
