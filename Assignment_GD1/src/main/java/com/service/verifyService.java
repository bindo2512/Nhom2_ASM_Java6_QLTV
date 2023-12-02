package com.service;

import com.entity.accounts;

public interface verifyService {
    boolean verifyRegistration(String code);

    boolean verifyForgotPassword(accounts accounts);

    boolean verifyNotSamePassword(String username, accounts accounts);

    boolean verifyCheckout(String code);
}
