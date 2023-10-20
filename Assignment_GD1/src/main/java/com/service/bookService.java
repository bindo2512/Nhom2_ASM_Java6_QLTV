package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.books;


public interface bookService {
    List<books> findAll();

    books findById(Integer id);

    books create(books book);
}
