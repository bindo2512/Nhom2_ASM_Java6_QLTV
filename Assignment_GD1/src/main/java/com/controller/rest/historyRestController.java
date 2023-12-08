package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.history;
import com.service.historyService;

@RestController
@RequestMapping("/rest/history")
public class historyRestController {

    @Autowired
    historyService service;
    @GetMapping("/{username}")
    public List<history> findAllHistory(@PathVariable("username") String username) {
        return service.findHistoryByUsername(username);
    }
}
