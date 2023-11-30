package com.service;

import java.util.List;
import java.util.Optional;

import com.entity.accounts;
import com.fasterxml.jackson.databind.JsonNode;


public interface accountService {
    List<accounts> findAll();

    accounts createNewAccount(accounts accounts);

    accounts updateAccount(accounts accounts);

    accounts updateLastLogin(String username);

    accounts updateLogout();

    accounts forgotPasswordChange(accounts accounts, String password);

    Optional<accounts> findAccountById(String username);
}
