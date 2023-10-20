package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.entity.accounts;
import com.service.accountService;

@Service
public class accoutServiceImpl implements accountService {

    @Autowired
    accountDAO dao;

    @Override
    public List<accounts> findAll() {
        return dao.findAll();
    }
    
}
