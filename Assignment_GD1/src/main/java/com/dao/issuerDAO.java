package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.issuer;

public interface issuerDAO extends JpaRepository<issuer, Integer> {
    
}
