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

import com.dao.bookDAO;
import com.dao.historyDAO;

@CrossOrigin("*")
@RestController
@RequestMapping("/rest/dashboard/book")
public class booksDashboardRestController {
    @Autowired
    bookDAO dao;

    @Autowired
    historyDAO hDAO;

    @GetMapping("/countbookbymonth")
    public List<Map<String, Object>> registrationPerMonth() {
        List<Object[]> result = dao.countCreatedBookByMonth();
        Map<Integer, Long> monthCounts = new HashMap<>();
        IntStream   .rangeClosed(1, 12).forEach(month -> monthCounts.put(month, 0L));
        List<Map<String, Object>> resultList = new ArrayList<>();

        for (Object[] row : result) {
            Integer month = (Integer) row[1]; 
            Long numberOfAdded = (Long) row[0]; 
            monthCounts.put(month, numberOfAdded);
        }

        monthCounts.forEach((month, count) -> {
            Map<String, Object> rowMap = new HashMap<>();
            rowMap.put("numberOfAdded", count);
            rowMap.put("month", month);
            resultList.add(rowMap);
        });
        return resultList;
    }

    @GetMapping("/countreadbyname")
    public List<Map<String, Object>> countBookReadByName() {
        List<Object[]> result = hDAO.countNumberOfReadByBook();
        List<Map<String, Object>> resultList = new ArrayList<>();
        for (Object[] row: result) {
            Map<String, Object> rowMap = new HashMap<>();
            Long numberOfRead = (Long) row[0];
            String bookname = (String) row[1]; 
            rowMap.put("numberOfRead", numberOfRead);
            rowMap.put("bookname", bookname);
            resultList.add(rowMap);
        }
        return resultList;
    }
}
