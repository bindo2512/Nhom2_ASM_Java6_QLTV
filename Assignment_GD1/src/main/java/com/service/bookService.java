package com.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;

import com.entity.books;
import com.entity.comments;


public interface bookService {
    List<books> findAll();

    books findById(Integer id);

    books createNewBook(books books);

    List<books> findBooksByName(String bookName);

    books update(books book);

    List<comments> getAllCommentByBookid(Integer id);

    Page<books> findBookByCriteria(Integer authorid, Integer publishersid, Integer categoriesid, String sortBy,String booknamekeyword, int page, int page_size);

    Page<books> findTop5Lastest();
}
