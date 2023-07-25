package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.entity.Account;

public interface accountDAO extends JpaRepository<Account, String> {

}
