package com.service;

import java.util.List;

import com.entity.history;

public interface historyService {
    history save(history history);

    List<history> findHistoryByUsername(String username);
}
