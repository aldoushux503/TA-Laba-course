package com.labas.store.dao.mybatis;

import com.labas.store.dao.IOrderProductDAO;
import com.labas.store.mapper.IOrderProductMapper;
import com.labas.store.model.entities.OrderProduct;
import com.labas.store.util.CompositeKey;

public class MyBatisOrderProductDAO extends MyBatisAbstractDAO<OrderProduct, CompositeKey<Long, Long>> implements IOrderProductDAO {

    public MyBatisOrderProductDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IOrderProductMapper.class;
    }
}
