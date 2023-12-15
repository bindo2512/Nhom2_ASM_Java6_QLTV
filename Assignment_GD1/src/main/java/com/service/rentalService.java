package com.service;

import java.util.Date;
import java.util.List;

import com.entity.details;
import com.entity.retails;
import com.fasterxml.jackson.databind.JsonNode;

public interface rentalService {

    retails create(JsonNode orderData);

    List<details> findById(Integer id);

    List<retails> findAll();

    retails updateSRetails(retails retail);

    List<retails> findByAccountUsername(String username);
    
    List<details> findAllRDetails();

    List<retails> findAllRetailById(Integer id);

    retails findRetailsById(Integer id);

    List<retails> findRetailsByVerifyState(boolean verifystate);

    List<retails> findRetailsByAdminVerify(boolean verifystate);

    List<retails> findRetailNearExpireDay(String username);

    List<retails> findRetailNeedUserverify(String username);
    
    void updateCancleRentals();

    void updateInvalidateRentals();
}
