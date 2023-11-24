package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.publishers;

public interface publishersDAO extends JpaRepository<publishers, Integer> {
    
}
