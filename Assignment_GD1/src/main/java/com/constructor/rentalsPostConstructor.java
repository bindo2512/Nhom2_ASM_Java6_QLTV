package com.constructor;

import java.sql.Date;
import java.time.LocalDate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dao.retailDAO;

@Component
public class rentalsPostConstructor implements CommandLineRunner {

    @Autowired
    private retailDAO dao;

    @Override
    public void run(String... args) {
        new Thread(() -> {
            while (true) {
                Date date = Date.valueOf(LocalDate.now());
                dao.disableExpiredRentals(date);
                dao.remindIllegalRentals(date);
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
    }
}
