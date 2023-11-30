package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.accountdetailDAO;
import com.entity.accountdetail;
import com.service.accountdetailService;

@Service
public class accountdetailServiceImpl implements accountdetailService{

    @Autowired
    accountdetailDAO dao;

    @Override
    public List<accountdetail> findAll() {
        return dao.findAll();
    }

    @Override
    public accountdetail creatNewAD(accountdetail accountdetail) {
        return dao.save(accountdetail);
    }

    @Override
    public accountdetail updateAD(accountdetail accountdetail) {
        return dao.save(accountdetail);
    }
    
}
