package com.labas.store.dao;

import com.labas.store.model.entity.ProductCategory;
import com.labas.store.util.CompositeKey;

/**
 * Interface for ProductCategory-specific DAO operations.
 */
public interface IProductCategoryDAO extends IGenericDAO<ProductCategory, CompositeKey<Long, Long>> {

}