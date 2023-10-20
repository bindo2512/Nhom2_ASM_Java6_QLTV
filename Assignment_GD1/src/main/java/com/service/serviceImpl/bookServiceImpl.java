package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.bookDAO;
import com.entity.books;
import com.service.bookService;

@Service
public class bookServiceImpl implements bookService {
    @Autowired
    bookDAO dao;

    @Override
    public List<books> findAll(){
        return dao.findAll();
    }

    @Override
    public books findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public books create(books book) {
        return dao.save(book);
    }
}
