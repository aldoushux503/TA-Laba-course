package com.labas.store.dao.mybatis;

import com.labas.store.dao.IProductDAO;
import com.labas.store.mapper.IProductMapper;
import com.labas.store.model.entities.Product;

public class MyBatisProductDAO extends MyBatisAbstractDAO<Product, Long> implements IProductDAO {

    public MyBatisProductDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IProductMapper.class;
    }
}
