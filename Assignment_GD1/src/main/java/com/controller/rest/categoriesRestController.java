package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.categories;
import com.service.categoriesService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/categories")
public class categoriesRestController {
    @Autowired
    categoriesService cservice;

    @GetMapping()
    public List<categories> getAll() {
        return cservice.findAll();
    }
}
