package com.service;

import java.util.List;

import com.entity.accountdetail;

public interface accountdetailService {
    List<accountdetail> findAll();

    accountdetail creatNewAD(accountdetail accountdetail);

    accountdetail updateAD(accountdetail accountdetail);
}
