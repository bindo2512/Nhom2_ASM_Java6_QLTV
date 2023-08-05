package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Book;

public interface bookDAO extends JpaRepository<Book, Integer> {
    
}
