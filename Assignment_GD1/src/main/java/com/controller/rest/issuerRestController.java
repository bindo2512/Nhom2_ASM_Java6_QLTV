package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
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

    @PostMapping()
    public issuer create(@RequestBody issuer issuer) {
        return service.createNewIssuer(issuer);
    }

    @PutMapping("{id}")
    public issuer update(@PathVariable("id") Integer id, @RequestBody issuer issuer) {
        return service.updateIssuer(issuer);
    }
}
