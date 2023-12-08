package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.accountDAO;
import com.dao.historyDAO;
import com.entity.history;
import com.service.historyService;

@Service
public class historyServiceImpl implements historyService {

    @Autowired
    historyDAO dao;

    @Autowired
    accountDAO aDAO;

    @Override
    public history save(history history) {
        return dao.save(history);
    }

    @Override
    public List<history> findHistoryByUsername(String username) {
        return aDAO.findById(username).get().getHistory();
    }
    
}
