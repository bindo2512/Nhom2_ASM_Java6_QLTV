package com.controller.rest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
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

}
