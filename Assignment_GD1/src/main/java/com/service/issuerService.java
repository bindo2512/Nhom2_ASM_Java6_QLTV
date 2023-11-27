package com.service;

import java.util.List;

import com.entity.issuer;

public interface issuerService {
    List<issuer> findAll();

    issuer createNewIssuer(issuer issuer);

    issuer updateIssuer(issuer issuer);
}
