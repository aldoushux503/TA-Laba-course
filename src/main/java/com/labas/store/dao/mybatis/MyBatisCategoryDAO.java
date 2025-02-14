package com.labas.store.dao.mybatis;

import com.labas.store.dao.ICategoryDAO;
import com.labas.store.mapper.ICategoryMapper;
import com.labas.store.model.entities.Category;

public class MyBatisCategoryDAO extends MyBatisAbstractDAO<Category, Long> implements ICategoryDAO {

    public MyBatisCategoryDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return ICategoryMapper.class;
    }
}
