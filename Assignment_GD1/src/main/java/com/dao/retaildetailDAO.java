package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Detail;

public interface retaildetailDAO extends JpaRepository<Detail, Integer> {
    
}
