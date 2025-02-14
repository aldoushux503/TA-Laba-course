package com.labas.store.dao.mybatis;

import com.labas.store.dao.IUserDAO;
import com.labas.store.mapper.IUserMapper;
import com.labas.store.model.entities.User;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class MyBatisUserDAO extends MyBatisAbstractDAO<User, Long> implements IUserDAO {

    @Override
    protected Class<?> getMapperClass() {
        return IUserMapper.class;
    }

}
