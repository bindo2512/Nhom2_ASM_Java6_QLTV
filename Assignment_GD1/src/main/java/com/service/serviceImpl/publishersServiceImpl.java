package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.publishersDAO;
import com.entity.publishers;
import com.service.publishersService;

@Service
public class publishersServiceImpl implements publishersService {
    @Autowired
    publishersDAO dao;

    @Override
    public List<publishers> findAll() {
        return dao.findAll();
    }

    
}
