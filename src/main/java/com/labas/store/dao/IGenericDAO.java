package com.labas.store.dao;

import com.labas.store.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T, ID> {
    Optional<T> findById(ID id) ;
    List<T> findAll();
    boolean save (T entity);

    boolean update(T entity);
    boolean delete(ID id);

}