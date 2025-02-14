package com.labas.store.dao.mybatis;

import com.labas.store.dao.IOrderDAO;
import com.labas.store.dao.IOrderStatusDAO;
import com.labas.store.dao.IUserDAO;
import com.labas.store.mapper.IOrderMapper;
import com.labas.store.model.entities.Order;

public class MyBatisOrderDAO extends MyBatisAbstractDAO<Order, Long> implements IOrderDAO {

    private final IOrderStatusDAO orderStatusDAO;
    private final IUserDAO userDAO;


    public MyBatisOrderDAO(IOrderStatusDAO orderStatusDAO, IUserDAO userDAO) {
        super();
        this.orderStatusDAO = orderStatusDAO;
        this.userDAO = userDAO;
    }


    @Override
    protected Class<?> getMapperClass() {
        return IOrderMapper.class;
    }
}
