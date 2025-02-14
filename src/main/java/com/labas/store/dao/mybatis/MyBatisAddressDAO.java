package com.labas.store.dao.mybatis;

import com.labas.store.dao.IAddressDAO;
import com.labas.store.dao.IUserDAO;
import com.labas.store.mapper.IAddressMapper;
import com.labas.store.model.entities.Address;

public class MyBatisAddressDAO extends MyBatisAbstractDAO<Address, Long> implements IAddressDAO {

    private IUserDAO userDAO;
    public MyBatisAddressDAO(IUserDAO userDAO) {
        super();
        this.userDAO = userDAO;
    }

    @Override
    protected Class<?> getMapperClass() {
        return IAddressMapper.class;
    }
}
