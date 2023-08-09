package com.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.entity.Book;


public interface bookService {
    List<Book> findAll();

    Book findById(Integer id);

    Book create(Book book);
}
