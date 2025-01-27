package com.labas.store.dao;

import java.util.List;

public interface GenericDAO <T, ID> {
    T findByID(ID id);
    List<T> findAll();
    boolean add (T entity);

    boolean update(T entity);
    boolean delete(ID id);

}