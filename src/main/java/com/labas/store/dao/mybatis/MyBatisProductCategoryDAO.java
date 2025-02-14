package com.labas.store.dao.mybatis;

import com.labas.store.dao.IProductCategoryDAO;
import com.labas.store.mapper.IProductCategoryMapper;
import com.labas.store.model.entities.ProductCategory;
import com.labas.store.util.CompositeKey;

public class MyBatisProductCategoryDAO extends MyBatisAbstractDAO<ProductCategory, CompositeKey<Long, Long>> implements IProductCategoryDAO {

    public MyBatisProductCategoryDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IProductCategoryMapper.class;
    }
}
