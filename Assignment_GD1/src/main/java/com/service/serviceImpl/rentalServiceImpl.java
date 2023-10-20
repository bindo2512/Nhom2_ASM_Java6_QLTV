package com.service.serviceImpl;


import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.dao.retailDAO;
import com.dao.retaildetailDAO;
import com.entity.retails;
import com.entity.details;
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

    @Autowired
    accountDAO adao;

    @Override
    public retails create(JsonNode orderData) {
        ObjectMapper mapper = new ObjectMapper();
        retails retail = mapper.convertValue(orderData, retails.class);
        dao.save(retail);

        TypeReference<List<details>> type = new TypeReference<List<details>>() {};
        List<details> details = mapper.convertValue(orderData.get("rdetail"), type)
        .stream()
        .peek(r -> r.setRetails(retail))
        .collect(Collectors.toList());
        ddao.saveAll(details);

        return retail;
    }

    @Override
    public List<details> findById(Integer id) {
        return dao.findById(id).get().getDetails();
    }

    @Override
    public List<retails> findAll() {
        return dao.findAll();
    }

    @Override
    public retails update(retails retail) {
        return dao.save(retail);
    }

    @Override
    public List<retails> findByAccountUsername(String username) {
        return adao.findById(username).get().getRetail();
    }

}
