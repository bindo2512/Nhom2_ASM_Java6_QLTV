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

    @Modifying
    @Query("select count(*) as number_of_registration, MONTH(a.createdate) as month from accounts a group by MONTH(a.createdate)")
    List<Object[]> countRegistrationInMonth();

    @Modifying
    @Query("select count(*) as number_of_active_per_month, MONTH(a.lastlogin) as month from accounts a group by MONTH(a.lastlogin)")
    List<Object[]> countActiveAccountInMonth();

    @Modifying
    @Query("select count(*) as number_of_active_per_day, DAY(a.lastlogin) as day from accounts a group by DAY(a.lastlogin)")
    List<Object[]> countActiveAccountInDay();
    
    @Modifying
    @Query("select count(*) as number_of_registration_in_day, DAY(a.createdate) as day from accounts a group by DAY(a.createdate)")
    List<Object[]> countRegistrationInDay();
}
