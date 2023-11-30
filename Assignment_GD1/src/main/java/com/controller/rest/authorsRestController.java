package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
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
    public authors create(@Validated @RequestBody authors author, Errors errors, Model model) {
        if (errors.hasErrors()) {
            model.addAttribute("message", "Vui lòng nhập những thông tin cần thiết");
            return null;
        }
        return service.createNewAuthors(author);
    }

    @PutMapping("{id}")
    public authors updateAuthor(@PathVariable("id") Integer id, @RequestBody authors authors){
        return service.updateAuthor(authors);
    }
}
