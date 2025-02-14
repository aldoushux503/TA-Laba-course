package com.labas.store.dao.mybatis;

import com.labas.store.dao.IOrderStatusDAO;
import com.labas.store.mapper.IOrderStatusMapper;
import com.labas.store.model.entities.OrderStatus;

public class MyBatisOrderStatusDAO extends MyBatisAbstractDAO<OrderStatus, Long> implements IOrderStatusDAO {

    public MyBatisOrderStatusDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IOrderStatusMapper.class;
    }
}
