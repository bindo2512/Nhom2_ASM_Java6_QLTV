package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.authors;
import com.service.authorsService;

@RestController
@RequestMapping("/rest/authors")
public class authorsRestController {
    @Autowired
    authorsService service;

    @GetMapping()
    public List<authors> getAll() {
        return service.findAll();
    }

    @PostMapping
    public authors create(@RequestBody authors author) {
        return service.createNewAuthors(author);
    }
}
