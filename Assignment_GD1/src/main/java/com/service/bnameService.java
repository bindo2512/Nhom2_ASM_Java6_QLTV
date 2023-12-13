package com.service;

import java.util.List;

import com.entity.bname;
import com.entity.books;

public interface bnameService {
    List<bname> findAll();

    bname createNewBname(bname banme);

    bname updateBname(bname bname);

    List<books> findBooksByBookname(String bookname);
}
