package com.service.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.retailDAO;
import com.dao.retaildetailDAO;
import com.entity.Retail;
import com.entity.Detail;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.service.rentalService;


@Service
public class rentalServiceImpl implements rentalService {
    @Autowired
    retailDAO dao;

    @Autowired
    retaildetailDAO ddao;

    @Override
    public Retail create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        Retail retail = mapper.convertValue(orderData, Retail.class);
        dao.save(retail);

        TypeReference<List<Detail>> type = new TypeReference<List<Detail>>() {};
        List<Detail> details = mapper.convertValue(orderData.get("rdetail"), type)
        .stream()
        .peek(r -> r.setRetail(retail))
        .collect(Collectors.toList());
        ddao.saveAll(details);

        return retail;
    }

    @Override
    public Object findById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public List<Retail> findAll() {
        return dao.findAll();
    }

    @Override
    public Retail update(Retail retail) {
        return dao.save(retail);
    }
}
