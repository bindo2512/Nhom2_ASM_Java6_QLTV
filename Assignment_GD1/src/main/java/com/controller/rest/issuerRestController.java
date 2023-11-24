package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.issuer;
import com.service.issuerService;

@RestController
@RequestMapping("/rest/issuer")
public class issuerRestController {
    @Autowired
    issuerService service;

    @GetMapping()
    public List<issuer> getAll() {
        return service.findAll();
    }
}
