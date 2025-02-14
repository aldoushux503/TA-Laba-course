package com.labas.store.dao.mybatis;

import com.labas.store.dao.ICarrierDAO;
import com.labas.store.mapper.ICarrierMapper;
import com.labas.store.model.entities.Carrier;

public class MyBatisCarrierDAO extends MyBatisAbstractDAO<Carrier, Long> implements ICarrierDAO {

    public MyBatisCarrierDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return ICarrierMapper.class;
    }
}
