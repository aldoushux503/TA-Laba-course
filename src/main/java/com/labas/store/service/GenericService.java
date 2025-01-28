package com.labas.store.service;

import com.labas.store.exception.ServiceException;

import java.util.List;
import java.util.Optional;

/**
 * Generic service interface providing basic CRUD operations.
 */
public interface GenericService<T, ID> {
    Optional<T> findById(ID id) throws ServiceException;
    List<T> findAll() throws ServiceException;
    boolean save(T entity) throws ServiceException;
    boolean update(T entity) throws ServiceException;
    boolean delete(ID id) throws ServiceException;
}