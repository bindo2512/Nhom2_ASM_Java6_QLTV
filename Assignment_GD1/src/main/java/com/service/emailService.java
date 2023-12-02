package com.service;

import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.mail.MessagingException;

import com.entity.accounts;
import com.entity.details;
import com.entity.forgotPassword;
import com.entity.retails;

public interface emailService {
    void sendVerificationEmail(accounts accounts) throws Exception;

    void sendForgotPasswordVerificationEmail(accounts accounts, forgotPassword fp) throws Exception;

    void sendRentailVerification(retails retails, List<details> details) throws Exception;
}
