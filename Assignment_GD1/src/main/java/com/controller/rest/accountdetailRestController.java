package com.controller.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.entity.accountdetail;
import com.service.accountService;
import com.service.accountdetailService;

@RestController
@RequestMapping("/rest/accountdetail")
public class accountdetailRestController {
   
    @Autowired
    accountdetailService service;

    @GetMapping()
    public List<accountdetail> getAll() {
        return service.findAll();
    }

    @PostMapping()
    public accountdetail createNewAD(@RequestBody accountdetail accountdetail) {
        return service.creatNewAD(accountdetail);
    }

    @PutMapping("{id}")
    public accountdetail updateAD(@PathVariable("id") Integer id, @RequestBody accountdetail accountdetail) {
        return service.creatNewAD(accountdetail);
    }

}
