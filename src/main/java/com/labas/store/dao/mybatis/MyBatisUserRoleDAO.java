package com.labas.store.dao.mybatis;

import com.labas.store.dao.IUserRoleDAO;
import com.labas.store.mapper.IUserRoleMapper;
import com.labas.store.model.entities.UserRole;
import com.labas.store.util.CompositeKey;

public class MyBatisUserRoleDAO extends MyBatisAbstractDAO<UserRole, CompositeKey<Long, Long>> implements IUserRoleDAO {

    public MyBatisUserRoleDAO() {
        super();
    }

    @Override
    protected Class<?> getMapperClass() {
        return IUserRoleMapper.class;
    }
}
