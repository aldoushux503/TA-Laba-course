package com.labas.store.service.impl;

import com.labas.store.service.AbstractService;
import com.labas.store.service.ProductService;

public class ProductServiceImpl extends AbstractService<Product, Long> implements ProductService {
    public ProductServiceImpl(ProductDAO productDAO) {
        super(productDAO);
    }
}
