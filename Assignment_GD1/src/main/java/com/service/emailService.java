package com.service;

import java.io.UnsupportedEncodingException;

import javax.mail.MessagingException;

import com.entity.accounts;
import com.entity.forgotPassword;

public interface emailService {
    void sendVerificationEmail(accounts accounts) throws Exception;

    void sendForgotPasswordVerificationEmail(accounts accounts, forgotPassword fp) throws Exception;
}
