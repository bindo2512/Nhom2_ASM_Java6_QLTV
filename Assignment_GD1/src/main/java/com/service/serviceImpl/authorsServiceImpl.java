package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.authorsDAO;
import com.entity.authors;
import com.service.authorsService;

@Service
public class authorsServiceImpl implements authorsService {
    
    @Autowired
    authorsDAO dao;

    @Override
    public List<authors> findAll() {
       return dao.findAll();
    }
}
