package com.service.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.retailDAO;
import com.entity.Retail;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.rentalService;

@Service
public class rentalServiceImpl implements rentalService {
    @Autowired
    retailDAO dao;

    @Override
    public Retail create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Retail retail = mapper.convertValue(orderData, Retail.class);
        return retail;
    }

    @Override
    public Object findById(Integer id) {
        return dao.findById(id);
    }
}
