package com.service;

import java.util.List;

import com.entity.bname;

public interface bnameService {
    List<bname> findAll();

    bname createNewBname(bname banme);

    bname updateBname(bname bname);
}
