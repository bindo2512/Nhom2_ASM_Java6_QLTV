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

    @PostMapping() 
    public categories create(@RequestBody categories categories) {
        return cservice.createNewCategory(categories);
    }
    
    @PutMapping("{id}")
    public categories update(@PathVariable("id") Integer id, @RequestBody categories categories) {
        return cservice.updateCategory(categories);
    }
}
