package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.categoriesDAO;
import com.entity.Categories;
import com.service.categoriesService;

@Service
public class categoriesImpl implements categoriesService{

    @Autowired
    categoriesDAO dao;

    @Override
    public List<Categories> findAll() {
        return dao.findAll();
    }
    
}
