package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.authors;

public interface authorsDAO extends JpaRepository<authors, Integer> {
    
}
