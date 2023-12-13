package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.bnameDAO;
import com.entity.bname;
import com.entity.books;
import com.service.bnameService;

@Service
public class bnameSerivceImpl implements bnameService{

    @Autowired
    bnameDAO dao;

    @Override
    public List<bname> findAll() {
        // TODO Auto-generated method stub
        return dao.findAll();
    }

    @Override
    public bname createNewBname(bname bname) {
        return dao.save(bname);
    }

    @Override
    public bname updateBname(bname bname) {
        return dao.save(bname);
    }

    @Override
    public List<books> findBooksByBookname(String bookname) {
        return dao.findByBookname(bookname);
    }
    
}
