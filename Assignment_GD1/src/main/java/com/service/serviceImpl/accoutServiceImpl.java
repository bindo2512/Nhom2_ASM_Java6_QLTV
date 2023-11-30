package com.service.serviceImpl;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.dao.accountdetailDAO;
import com.entity.accountdetail;
import com.entity.accounts;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.accountService;


import aj.org.objectweb.asm.Type;

@Service
public class accoutServiceImpl implements accountService {

    static String usernameCurrent;

    @Autowired
    BCryptPasswordEncoder pe;

    @Autowired
    accountDAO dao;

    @Autowired 
    accountdetailDAO ddao;

    @Override
    public List<accounts> findAll() {
        return dao.findAll();
    }

    @Override
    public accounts createNewAccount(accounts accounts) {
        ddao.save(accounts.getAccountdetail());
        return dao.save(accounts);
    }

    @Override
    public accounts updateAccount(accounts accounts) {
        ddao.save(accounts.getAccountdetail());
        return dao.save(accounts);
    }

    @Override
    public accounts updateLastLogin(String username) {
        usernameCurrent = username;
        accounts accounts2 = dao.findById(username).get();
        Date date = new Date();
        // java.sql.Date dateSQL = new java.sql.Date(date.getTime());
        // System.out.println(date);
        accounts2.setLastlogin(date);
        accounts2.setCurrentlylogin(true);
        return dao.save(accounts2);
    }

    @Override
    public accounts updateLogout() {
        accounts accounts2 = dao.findById(usernameCurrent).get();
        accounts2.setCurrentlylogin(false);
        return dao.save(accounts2);
    }

    @Override
    public accounts forgotPasswordChange(accounts accounts, String password) {
        accounts.setPassword(pe.encode(password));
        return dao.save(accounts);
    }

    @Override
    public Optional<accounts> findAccountById(String username) {
        return dao.findById(username);
    }


    


    
}
