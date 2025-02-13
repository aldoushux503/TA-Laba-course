package com.labas.store.mapper;

import java.util.List;
import java.util.Optional;

public interface IGenericMapper<T, ID> {

    T findById(ID id) ;
    List<T> findAll();
    boolean save (T entity);

    boolean update(T entity);
    boolean delete(ID id);
}
