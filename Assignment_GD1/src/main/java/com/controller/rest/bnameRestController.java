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

import com.entity.bname;
import com.service.bnameService;

@RestController
@RequestMapping("/rest/bname")
public class bnameRestController {
    @Autowired
    bnameService service;

    @GetMapping
    public List<bname> getAll() {
        return service.findAll();
    }

    @PostMapping
    public bname create(@RequestBody bname bname) {
        return service.createNewBname(bname);
    }

    @PutMapping("{id}")
    public bname update(@PathVariable("id") Integer id, @RequestBody bname bname) {
        return service.updateBname(bname);
    }
}
