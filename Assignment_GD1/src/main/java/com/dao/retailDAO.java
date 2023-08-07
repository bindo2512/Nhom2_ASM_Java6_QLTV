package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Retail;

public interface retailDAO extends JpaRepository<Retail, Integer> {
    
}
