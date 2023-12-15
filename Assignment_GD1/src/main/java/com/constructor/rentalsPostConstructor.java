package com.constructor;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.service.rentalService;

@Component
public class rentalsPostConstructor implements CommandLineRunner {
    @Autowired
    rentalService service;

    @Override
    public void run(String... args) throws Exception {
        service.updateCancleRentals();
        service.updateInvalidateRentals();
        System.out.println("Test chức năng");
        
    }

}
