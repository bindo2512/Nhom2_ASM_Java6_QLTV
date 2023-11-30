package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.comments;
import com.service.commentService;

@RestController
@RequestMapping("/rest/comments")
public class commentRestController {

    @Autowired
    commentService service;

    @GetMapping
    public List<comments> getAll() {
        return service.getAll();
    }
}
