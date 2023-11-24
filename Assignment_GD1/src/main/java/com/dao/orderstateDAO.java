package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.orderstate;

public interface orderstateDAO extends JpaRepository<orderstate, Integer> {
    
}
