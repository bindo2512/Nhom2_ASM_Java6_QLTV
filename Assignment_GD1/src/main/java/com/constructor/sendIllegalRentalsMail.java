package com.constructor;

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import com.dao.retailDAO;
import com.entity.accounts;
import com.entity.retails;
import com.service.accountService;
import com.service.emailService;
import com.service.rentalService;

@Component
public class sendIllegalRentalsMail implements CommandLineRunner {
    @Autowired
    retailDAO dao;

    @Autowired
    accountService aService;

    @Autowired
    rentalService rService;

    @Autowired
    emailService emailService;

    @Override
    public void run(String... args) throws Exception {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);
        calendar.set(Calendar.MILLISECOND, 0);

        Date dateSchedule = calendar.getTime();
        long period = 24 * 60 * 60 * 1000;
        TimerTask timerTask = new TimerTask() {
            @Override
            public void run() {
                List<Object[]> findUsername = dao.findAllUsernameHaveExpireRentals();
                for (Object[] row: findUsername) {
                    String username = (String) row[0];
                    accounts account = aService.findAccountById(username).get();
                    List<retails> retails = rService.findIllegalRentalsByUsername(username);
                    try {
                        emailService.sendNotificationAboutIllegalRental(account, retails);
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
            }
            
        };
        Timer timer = new Timer();
        timer.schedule(timerTask, dateSchedule, period);

    }
    
}
