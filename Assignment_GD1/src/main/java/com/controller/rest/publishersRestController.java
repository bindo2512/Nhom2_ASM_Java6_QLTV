package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.publishers;
import com.service.publishersService;

@RestController
@RequestMapping("/rest/publishers")
public class publishersRestController {
    @Autowired
    publishersService service;

    @GetMapping()
    public List<publishers> getAll() {
        return service.findAll();
    }
}
