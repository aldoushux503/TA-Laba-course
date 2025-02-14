package com.labas.store.dao.mybatis;

import com.labas.store.dao.IShippingStatusDAO;
import com.labas.store.mapper.IShippingStatusMapper;
import com.labas.store.model.entities.ShippingStatus;

public class MyBatisShippingStatusDAO extends MyBatisAbstractDAO<ShippingStatus, Long> implements IShippingStatusDAO {

    public MyBatisShippingStatusDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IShippingStatusMapper.class;
    }
}
