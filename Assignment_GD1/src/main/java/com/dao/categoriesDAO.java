package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Categories;

public interface categoriesDAO extends JpaRepository<Categories, Integer> {
    
}
