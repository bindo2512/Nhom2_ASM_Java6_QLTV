package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.retails;

public interface retailDAO extends JpaRepository<retails, Integer> {
    
}
