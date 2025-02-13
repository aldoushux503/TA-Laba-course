package com.labas.store.dao.mybatis;

import com.labas.store.dao.IGenericDAO;
import org.apache.ibatis.session.SqlSessionFactory;

import java.util.List;
import java.util.Optional;

public class MyBatisAbstractDAO<T,Long> implements IGenericDAO<T,Long> {

    SqlSessionFactory sessionFactory;

     public MyBatisAbstractDAO(SqlSessionFactory sessionFactory) {
         this.sessionFactory = sessionFactory;
     }

    @Override
    public Optional<T> findById(Long aLong) {
        return Optional.empty();
    }

    @Override
    public List<T> findAll() {
        return null;
    }

    @Override
    public boolean save(T entity) {
        return false;
    }

    @Override
    public boolean update(T entity) {
        return false;
    }

    @Override
    public boolean delete(Long aLong) {
        return false;
    }
}
