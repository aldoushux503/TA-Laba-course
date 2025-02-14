package com.labas.store.dao.mybatis;

import com.labas.store.dao.IOrderDAO;
import com.labas.store.dao.IPaymentDAO;
import com.labas.store.dao.IPaymentMethodDAO;
import com.labas.store.dao.IUserDAO;
import com.labas.store.mapper.IPaymentMapper;
import com.labas.store.model.entities.Payment;

public class MyBatisPaymentDAO extends MyBatisAbstractDAO<Payment, Long> implements IPaymentDAO {

    private IPaymentMethodDAO paymentMethodDAO;
    private IOrderDAO orderDAO;
    private IUserDAO userDAO;

    public MyBatisPaymentDAO(IPaymentMethodDAO paymentMethodDAO, IOrderDAO orderDAO, IUserDAO userDAO) {
        super();
        this.paymentMethodDAO = paymentMethodDAO;
        this.orderDAO = orderDAO;
        this.userDAO = userDAO;
    }

    @Override
    protected Class<?> getMapperClass() {
        return IPaymentMapper.class;
    }
}
