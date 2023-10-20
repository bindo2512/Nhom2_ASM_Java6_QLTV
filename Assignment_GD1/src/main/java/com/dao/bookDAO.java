package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.books;

public interface bookDAO extends JpaRepository<books, Integer> {
    
}
