package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.orderstate;
import com.service.orderstateService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orderstate")
public class orderstateRestController {

    @Autowired
    orderstateService service;

    @GetMapping()
    public List<orderstate> findAll() {
        return service.findAll();
    }
}
