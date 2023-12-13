package com.service;

import java.util.List;

import com.entity.comments;

public interface commentService {
    List<comments> getAll();

    comments saveComment(comments comments);

    void delete(Integer id);
}
