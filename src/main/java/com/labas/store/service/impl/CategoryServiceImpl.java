package com.labas.store.service.impl;

import com.labas.store.dao.ICategoryDAO;
import com.labas.store.model.entity.Category;
import com.labas.store.service.ICategoryService;

public class CategoryServiceImpl extends AbstractService<Category, Long> implements ICategoryService {
    public CategoryServiceImpl(ICategoryDAO ICategoryDAO) {
        super(ICategoryDAO);
    }
}
