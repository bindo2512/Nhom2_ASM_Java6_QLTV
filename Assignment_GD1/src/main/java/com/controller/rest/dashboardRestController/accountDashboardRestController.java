package com.controller.rest.dashboardRestController;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.accountDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/dashboard/account")
public class accountDashboardRestController {
    @Autowired
    accountDAO dao;

    @GetMapping("/registrationpermonth")
    public List<Map<String, Object>> registrationPerMonth() {
        List<Object[]> result = dao.countRegistrationInMonth();
        Map<Integer, Long> monthCounts = new HashMap<>();
        IntStream.rangeClosed(1, 12).forEach(month -> monthCounts.put(month, 0L));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : result) {
            Integer month = (Integer) row[1]; 
            Long numberOfRegistraion = (Long) row[0]; 
            monthCounts.put(month, numberOfRegistraion);
        }
        monthCounts.forEach((month, count) -> {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("numberOfRegistraion", count);
            rowMap.put("month", month);
            resultList.add(rowMap);
        });
        // for (Object[] row : result) {
        //     Map<String, Object> rowMap = new HashMap<>();
            
        //     Long numberOfRegistraion = (Long) row[0]; 
        //     Integer month = (Integer) row[1];       
            
        //     rowMap.put("numberOfRegistraion", numberOfRegistraion);
        //     rowMap.put("month", month);
            
        //     resultList.add(rowMap);
        // }
        return resultList;
    }

    @GetMapping("/registrationperday")
    public List<Map<String, Object>> registrationPerDay() {
        List<Object[]> result = dao.countRegistrationInDay();

        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : result) {
            Map<String, Object> rowMap = new HashMap<>();
            
            Long numberOfRegistraion = (Long) row[0];
            Integer day = (Integer) row[1];      
            
            rowMap.put("numberOfRegistraion", numberOfRegistraion);
            rowMap.put("day", day);
            
            resultList.add(rowMap);
        }
        return resultList;
    }

    @GetMapping("/activeaccountspermonth")
    public List<Map<String, Object>> activeAccountPerMonth() {
        List<Object[]> result = dao.countRegistrationInMonth();
        Map<Integer, Long> monthCounts = new HashMap<>();
        IntStream   .rangeClosed(1, 12).forEach(month -> monthCounts.put(month, 0L));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : result) {
            Integer month = (Integer) row[1]; 
            Long activeAccount = (Long) row[0]; 
            monthCounts.put(month, activeAccount);
        }
        monthCounts.forEach((month, count) -> {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("activeAccount", count);
            rowMap.put("month", month);
            resultList.add(rowMap);
        });
        return resultList;
    }

    @GetMapping("/activeaccountsperday")
    public List<Map<String, Object>> activeAccountPerDay() {
        List<Object[]> result = dao.countRegistrationInDay();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : result) {
            Map<String, Object> rowMap = new HashMap<>();
            
            Long activeAccount = (Long) row[0]; 
            Integer day = (Integer) row[1];      
            
            rowMap.put("activeAccount", activeAccount);
            rowMap.put("day", day);
            
            resultList.add(rowMap);
        }
        return resultList;
    }
}
