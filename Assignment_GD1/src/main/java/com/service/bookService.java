package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.entity.books;


public interface bookService {
    List<books> findAll();

    books findById(Integer id);

    books createNewBook(books books);

    List<books> findBooksByName(String bookName);


    Page<books> findBookByCriteria(Integer authorid, Integer publishersid, Integer categoriesid, String booknamekeyword, int page, int page_size);
}
