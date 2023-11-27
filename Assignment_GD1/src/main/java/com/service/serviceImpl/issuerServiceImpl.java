package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.issuerDAO;
import com.entity.issuer;
import com.service.issuerService;

@Service
public class issuerServiceImpl implements issuerService {
    @Autowired
    issuerDAO dao;

    @Override
    public List<issuer> findAll() {
        return dao.findAll();
    }

    @Override
    public issuer createNewIssuer(issuer issuer) {
        return dao.save(issuer);
    }

    @Override
    public issuer updateIssuer(issuer issuer) {
        return dao.save(issuer);
    }
    
    
}
