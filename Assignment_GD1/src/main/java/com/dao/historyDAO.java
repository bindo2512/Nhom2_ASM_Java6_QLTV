package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.history;

public interface historyDAO extends JpaRepository<history, Integer> {
    
}
