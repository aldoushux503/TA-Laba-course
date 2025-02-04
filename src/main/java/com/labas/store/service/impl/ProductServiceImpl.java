package com.labas.store.service.impl;

import com.labas.store.dao.IProductDAO;
import com.labas.store.model.entity.Product;
import com.labas.store.service.AbstractService;
import com.labas.store.service.IProductService;

public class ProductServiceImpl extends AbstractService<Product, Long> implements IProductService {
    public ProductServiceImpl(IProductDAO IProductDAO) {
        super(IProductDAO);
    }
}
