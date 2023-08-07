package com.service;

import com.entity.Retail;
import com.fasterxml.jackson.databind.JsonNode;

public interface rentalService {

    Retail create(JsonNode orderData);

    Object findById(Integer string);
    
}
