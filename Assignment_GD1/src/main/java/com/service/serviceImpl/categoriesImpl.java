package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.categoriesDAO;
import com.entity.categories;
import com.service.categoriesService;

@Service
public class categoriesImpl implements categoriesService{

    @Autowired
    categoriesDAO dao;

    @Override
    public List<categories> findAll() {
        return dao.findAll();
    }
    
}
