package com.service.serviceImpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.dao.retailDAO;
import com.entity.accounts;
import com.entity.retails;
import com.service.accountService;
import com.service.verifyService;

@Service
public class verifyRegistrationImpl implements verifyService{

    @Autowired
    accountDAO dao;

    @Autowired 
    retailDAO rDAO;

    @Autowired
    accountService aService;

    @Override
    public boolean verifyRegistration(String code) {
        accounts accounts = dao.findByVerification(code);
        if (accounts == null ) {
            return false;
        } else {
            dao.enable(accounts.getUsername());
            return true;
        }   
    }

    @Override
    public boolean verifyForgotPassword(accounts accounts) {
        Optional<accounts> checkForgot = dao.findById(accounts.getUsername());
        if (checkForgot.isPresent() == false || checkForgot.get().getIsactive() == false) {
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean verifyNotSamePassword(String username, accounts accounts) {
        Optional<accounts> checkSamePassAcc = dao.findById(username);
        if (checkSamePassAcc.isPresent() == false || accounts.getPassword().equals(checkSamePassAcc.get().getPassword())) {
            aService.forgotPasswordChange(checkSamePassAcc.get(), accounts.getPassword());
            return false;
        } else {
            return true;
        }
    }

    @Override
    public boolean verifyCheckout(String code) {
       retails retails = rDAO.findByVerification(code);
       if (retails == null) {
        return false;
       } else {
        rDAO.enable(retails.getRetailid());
        return true;
       }
    }
    
}
