package com.service.serviceImpl;


import java.sql.Date;
import java.time.LocalDate;
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

import net.bytebuddy.utility.RandomString;


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
        retail.setVerification(RandomString.make(64));
        retail.setIsverify(false);
        retail.setAdminverify(false);
        retail.setInvalidate(false);
        dao.save(retail);

        TypeReference<List<details>> type = new TypeReference<List<details>>() {};
        List<details> rental_detail = mapper.convertValue(orderData.get("details"), type)
        .stream()
        .peek(r -> r.setRetails(retail))
        .collect(Collectors.toList());
        ddao.saveAll(rental_detail);

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
    public retails updateSRetails(retails retail) {
        return dao.save(retail);
    }

    @Override
    public List<retails> findByAccountUsername(String username) {
        return adao.findById(username).get().getRetail();
    }


    @Override
    public List<details> findAllRDetails() {
        return ddao.findAll();
    }

    @Override
    public List<retails> findAllRetailById(Integer id) {
        return dao.findAll();
    }

    @Override
    public retails findRetailsById(Integer id) {
        return dao.findById(id).get();
    }

    @Override
    public List<retails> findRetailsByVerifyState(boolean verifystate) {
        return dao.findByIsverify(verifystate);
    }

    @Override
    public List<retails> findRetailsByAdminVerify(boolean verifystate) {
        return dao.findByAdminverify(verifystate);
    }

    @Override
    public List<retails> findRetailNearExpireDay(String username) {
        Date date = Date.valueOf(LocalDate.now());
        return dao.findNearExpireRetails(date,username);
    }

    @Override
    public List<retails> findRetailNeedUserverify(String username) {
        return dao.findRentalsNeedVerify(username);
    }

    @Override
    public void updateCancleRentals() {
        Date date = Date.valueOf(LocalDate.now());
        dao.disableExpiredRentals(date);
    }

    @Override
    public void updateInvalidateRentals() {
        Date date = Date.valueOf(LocalDate.now());
        dao.remindIllegalRentals(date);
    }
}
