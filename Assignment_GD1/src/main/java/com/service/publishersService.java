package com.service;

import java.util.List;

import com.entity.publishers;

public interface publishersService {
    List<publishers> findAll();

    publishers createNewPublisher(publishers publishers);

    publishers updatePublisher(publishers publishers);
}
