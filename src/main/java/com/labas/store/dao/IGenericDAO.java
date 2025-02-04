package com.labas.store.dao;

import com.labas.store.exception.DAOException;

import java.util.List;
import java.util.Optional;

public interface IGenericDAO<T, ID> {
    Optional<T> findById(ID id) throws DAOException;
    List<T> findAll() throws DAOException;
    boolean save (T entity) throws DAOException;

    boolean update(T entity) throws DAOException;
    boolean delete(ID id) throws DAOException;

}