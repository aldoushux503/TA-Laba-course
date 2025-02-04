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
    public Optional<T> findById(ID id) throws ServiceException {
        try {
            return dao.findById(id);
        } catch (DAOException e) {
            throw new ServiceException("Error fetching entity by ID: " + id, e);
        }
    }

    @Override
    public List<T> findAll() throws ServiceException {
        try {
            return dao.findAll();
        } catch (DAOException e) {
            throw new ServiceException("Error fetching all entities", e);
        }
    }

    @Override
    public boolean save(T entity) throws ServiceException {
        try {
            return dao.save(entity);
        } catch (DAOException e) {
            throw new ServiceException("Error saving entity: " + entity, e);
        }
    }

    @Override
    public boolean update(T entity) throws ServiceException {
        try {
            return dao.update(entity);
        } catch (DAOException e) {
            throw new ServiceException("Error updating entity: " + entity, e);
        }
    }

    @Override
    public boolean delete(ID id) throws ServiceException {
        try {
            return dao.delete(id);
        } catch (DAOException e) {
            throw new ServiceException("Error deleting entity by ID: " + id, e);
        }
    }
}
