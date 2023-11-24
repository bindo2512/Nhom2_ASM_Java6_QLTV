package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.orderstateDAO;
import com.entity.orderstate;
import com.service.orderstateService;

@Service
public class orderstateServiceImpl implements orderstateService {

    @Autowired
    orderstateDAO dao;

    @Override
    public List<orderstate> findAll() {
        return dao.findAll();
    }
    
}
