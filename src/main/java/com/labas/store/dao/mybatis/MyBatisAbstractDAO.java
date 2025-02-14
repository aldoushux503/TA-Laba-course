package com.labas.store.dao.mybatis;

import com.labas.store.dao.IGenericDAO;
import com.labas.store.util.MyBatisUtil;
import com.mysql.cj.Session;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public abstract class MyBatisAbstractDAO<T,Long> implements IGenericDAO<T,Long> {

    SqlSessionFactory sessionFactory;

     public MyBatisAbstractDAO() {
         this.sessionFactory =  MyBatisUtil.getSessionFactory();
     }

     protected abstract Class<?> getMapperClass();


    @Override
    public Optional<T> findById(Long id) {
        try (SqlSession session = sessionFactory.openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            Method method = getMapperClass().getMethod("findById", java.lang.Long.class);
            return (Optional<T>) method.invoke(mapper, id);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            return Optional.empty();
        }

    }

    @Override
    public List<T> findAll() {
        try (SqlSession session = sessionFactory.openSession()) {
            Object mapper = session.getMapper(getMapperClass());
            Method method = getMapperClass().getMethod("findAll");
            return (List<T>) method.invoke(mapper);
        } catch (InvocationTargetException | NoSuchMethodException | IllegalAccessException e) {
            e.printStackTrace();
            return new ArrayList<>();
        }
    }

    @Override
    public boolean save(T entity) {
        return executeUpdate("save", entity);
    }

    @Override
    public boolean update(T entity) {
        return executeUpdate("update", entity);
    }

    @Override
    public boolean delete(Long id) {
        return executeUpdate("delete", id);
    }

    private boolean executeUpdate(String methodName, Object param) {
        try (SqlSession session = sessionFactory.openSession(true)) {
            Object mapper = session.getMapper(getMapperClass());
            Method method = getMapperClass().getMethod(methodName, param.getClass());
            method.invoke(mapper, param);
            return true;
        } catch (Exception e) {
            e.printStackTrace();
            return false;
        }
    }
}
