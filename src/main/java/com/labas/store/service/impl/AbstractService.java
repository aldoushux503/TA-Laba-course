package com.labas.store.service.impl;

import com.labas.store.dao.IGenericDAO;
import com.labas.store.exception.DAOException;
import com.labas.store.exception.ServiceException;
import com.labas.store.service.IGenericService;

import java.util.List;
import java.util.Optional;

/**
 * Abstract service class implementing common service logic.
 */
public abstract class AbstractService<T, ID> implements IGenericService<T, ID> {
    protected final IGenericDAO<T, ID> dao;

    protected AbstractService(IGenericDAO<T, ID> dao) {
        this.dao = dao;
    }

    @Override
    public Optional<T> findById(ID id) {
        return dao.findById(id);
    }

    @Override
    public List<T> findAll() {
        return dao.findAll();
    }

    @Override
    public boolean save(T entity) {
        return dao.save(entity);
    }

    @Override
    public boolean update(T entity) throws ServiceException {
        return dao.update(entity);
    }

    @Override
    public boolean delete(ID id) throws ServiceException {
        return dao.delete(id);
    }
}
