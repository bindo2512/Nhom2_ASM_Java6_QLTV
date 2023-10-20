package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.categories;

public interface categoriesDAO extends JpaRepository<categories, Integer> {
    
}
