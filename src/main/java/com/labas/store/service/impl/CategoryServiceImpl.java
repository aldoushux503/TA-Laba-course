package com.labas.store.service.impl;

import com.labas.store.dao.CategoryDAO;
import com.labas.store.model.entity.Category;
import com.labas.store.service.AbstractService;
import com.labas.store.service.CategoryService;

public class CategoryServiceImpl extends AbstractService<Category, Long> implements CategoryService {
    public CategoryServiceImpl(CategoryDAO categoryDAO) {
        super(categoryDAO);
    }
}
