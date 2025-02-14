package com.labas.store.dao.mybatis;

import com.labas.store.dao.IRoleDAO;
import com.labas.store.mapper.IRoleMapper;
import com.labas.store.model.entities.Role;

public class MyBatisRoleDAO extends MyBatisAbstractDAO<Role, Long> implements IRoleDAO {

    public MyBatisRoleDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IRoleMapper.class;
    }
}
