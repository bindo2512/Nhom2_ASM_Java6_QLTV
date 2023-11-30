package com.controller.rest;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.accountDAO;
import com.entity.accounts;
import com.fasterxml.jackson.databind.JsonNode;
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

    @PostMapping()
    public accounts createNewAccounts(accounts accounts) {
        return service.createNewAccount(accounts);
    }

    @PutMapping("{username}")
    public accounts updateAccount(@PathVariable("username") String username,@RequestBody accounts accounts) {
        System.out.println(accounts.getAccountdetail());
        return service.updateAccount(accounts);
    }

    @GetMapping("{username}")
    public Optional<accounts> findByUsername(@PathVariable("username") String username) {
        return service.findAccountById(username);
    }

}
