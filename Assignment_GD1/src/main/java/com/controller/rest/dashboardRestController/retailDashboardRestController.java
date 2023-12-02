package com.controller.rest.dashboardRestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dao.retailDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/dashboard/retail")
public class retailDashboardRestController {
    @Autowired
    retailDAO retailDAO;

    @GetMapping("/retailByMonth")
    public List<Map<String, Object>> countRetailByMonth() {
        List<Object[]> result = retailDAO.countRetailsByMonth();
        Map<Integer, Long> monthCounts = new HashMap<>();
        IntStream.rangeClosed(1, 12).forEach(month -> monthCounts.put(month, 0L));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : result) {
            Integer retailMonth = (Integer) row[0]; 
            Long retailCount = (Long) row[1]; 
            monthCounts.put(retailMonth, retailCount);
        }
        monthCounts.forEach((month, count) -> {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("retailMonth", month);
            rowMap.put("retailCount", count);
            resultList.add(rowMap);
        });
        // for (Object[] row : result) {
        //     Map<String, Object> rowMap = new HashMap<>();
            
        //     Integer retailMonth = (Integer) row[0]; // Assuming the first element is retail_month
        //     Long retailCount = (Long) row[1];       // Assuming the second element is retail_count
            
        //     rowMap.put("retailMonth", retailMonth);
        //     rowMap.put("retailCount", retailCount);
            
        //     resultList.add(rowMap);
        // }
        return resultList;
    }

    @GetMapping("/retailByUsername") 
    public List<Map<String, Object>> countRetailByUsername() {
        List<Object[]> result = retailDAO.countRetailByUsername();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row : result) {
            Map<String, Object> rowMap = new HashMap<>();
            
            Long numberOfRetail = (Long) row[0];  
            String username = (String) row[1];       
            
            rowMap.put("numberOfRetail", numberOfRetail);
            rowMap.put("username", username);
            
            resultList.add(rowMap);
        }
        return resultList;
    }

    @GetMapping("/retailByBookname")
    public List<Map<String, Object>> countRetailByBookname() {
        List<Object[]> result = retailDAO.countRetailByBookname();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row: result) {
            Map<String, Object> rowMap = new HashMap<>();
            Long numberOfRetail = (Long) row[0];
            String bookname = (String) row[1];
            rowMap.put("numberOfRetail", numberOfRetail);
            rowMap.put("bookname", bookname);
            resultList.add(rowMap);
        }
        return resultList;
    }

    @GetMapping("/retailByUsernameInMonth")
    public List<Map<String, Object>> countRetailByUsernameInMonth() {
        List<Object[]> result = retailDAO.countRetailByUsernameCurrentMonth();
        Map<Integer, Long> monthCounts = new HashMap<>();
        IntStream.rangeClosed(1, 12).forEach(month -> monthCounts.put(month, 0L));
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row: result) {
            Map<String, Object> rowMap = new HashMap<>();
            Integer numberOfRetail = (Integer) row[0];
            String username = (String) row[1];
            rowMap.put("numberOfRetail", numberOfRetail);
            rowMap.put("username", username);
            resultList.add(rowMap);
        }
        return resultList;
    }


    @GetMapping("/retailByCategory")
    public List<Map<String, Object>> countRetailByCategory() {
        List<Object[]> result = retailDAO.countRetailByCategory();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for(Object[] row: result) {
            Map<String, Object> rowMap = new HashMap<>();
            Long numberOfRetail = (Long) row[0];
            String categoryname = (String) row[1];
            rowMap.put("numberOfRetail", numberOfRetail);
            rowMap.put("categoryname", categoryname);
            resultList.add(rowMap);
        }
        return resultList;
    }
}
