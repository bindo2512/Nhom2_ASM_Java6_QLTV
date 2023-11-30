package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.comments;

public interface commentDAO extends JpaRepository<comments, Integer>{
    
}
