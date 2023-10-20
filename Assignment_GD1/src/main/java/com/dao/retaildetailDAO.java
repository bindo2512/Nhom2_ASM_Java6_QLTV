package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.details;

public interface retaildetailDAO extends JpaRepository<details, Integer> {
    
}
