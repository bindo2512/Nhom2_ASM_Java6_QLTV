package com.service;

import java.util.List;

import com.entity.Retail;
import com.fasterxml.jackson.databind.JsonNode;

public interface rentalService {

    Retail create(JsonNode orderData);

    Object findById(Integer string);

    List<Retail> findAll();

    Retail update(Retail retail);
    
}
