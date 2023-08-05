package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.bookDAO;
import com.entity.Book;
import com.service.bookService;

@Service
public class bookServiceImpl implements bookService {
    @Autowired
    bookDAO dao;

    @Override
    public List<Book> findAll(){
        return dao.findAll();
    }

    @Override
    public Book findById(Integer id) {
        return dao.findById(id).get();
    }
}
