package com.service;

import java.util.List;

import com.entity.details;
import com.entity.retails;
import com.fasterxml.jackson.databind.JsonNode;

public interface rentalService {

    retails create(JsonNode orderData);

    List<details> findById(Integer string);

    List<retails> findAll();

    retails update(retails retail);

    List<retails> findByAccountUsername(String username);
    
}
