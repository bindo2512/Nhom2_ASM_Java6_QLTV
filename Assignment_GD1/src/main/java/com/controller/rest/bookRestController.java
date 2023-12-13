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

import com.entity.books;
import com.service.bookService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/book")
public class bookRestController {
    @Autowired
    bookService service;

    @GetMapping("/{bookid}")
    public books getOne(@PathVariable("bookid") Integer id) {
        return service.findById(id);
    }
    
    @GetMapping()
    public List<books> getAll() {
        return service.findAll();
    }

    @PutMapping("{id}")
    public books update(@PathVariable("id") Integer id, @RequestBody books book) {
        return service.update(book);
    } 

    @PostMapping()
    public books create(@RequestBody books book) {
        return service.createNewBook(book);
    }

    

}
