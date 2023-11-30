package com.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.entity.accounts;
import java.util.List;

import javax.transaction.Transactional;


public interface accountDAO extends JpaRepository<accounts, String> {
    @Transactional
    @Modifying
    @Query("Update accounts a set a.isactive = true, a.verification = null where a.username = ?1")
    public void enable(String username);

    @Query("Select a from accounts a where a.verification = ?1")
    accounts findByVerification(String verification);
}
