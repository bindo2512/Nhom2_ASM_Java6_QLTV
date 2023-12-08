package com.service.serviceImpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dao.commentDAO;
import com.entity.comments;
import com.service.commentService;

@Service
public class commentServiceImpl implements commentService {

    @Autowired
    commentDAO dao;

    @Override
    public List<comments> getAll() {
        return dao.findAll();
    }

    @Override
    public comments saveComment(comments comments) {
        return dao.save(comments);
    }
    
}
