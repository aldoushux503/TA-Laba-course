package com.labas.store.dao.mybatis;

import com.labas.store.dao.IPaymentMethodDAO;
import com.labas.store.mapper.IPaymentMethodMapper;
import com.labas.store.model.entities.PaymentMethod;

public class MyBatisPaymentMethodDAO extends MyBatisAbstractDAO<PaymentMethod, Long> implements IPaymentMethodDAO {

    public MyBatisPaymentMethodDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IPaymentMethodMapper.class;
    }
}
