package com.service;

import java.util.List;

import com.entity.categories;

public interface categoriesService {
    List<categories> findAll();

    categories createNewCategory(categories categories);

    categories updateCategory(categories categories);
}
