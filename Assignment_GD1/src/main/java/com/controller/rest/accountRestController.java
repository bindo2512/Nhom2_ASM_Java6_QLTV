package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.accountDAO;
import com.entity.accounts;
import com.service.accountService;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/account")
public class accountRestController {
    @Autowired
    accountService service;

    @GetMapping
    public List<accounts> getAll() {
        return service.findAll();
    }

}
