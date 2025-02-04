package com.labas.store.dao;

import com.labas.store.model.entity.OrderProduct;
import com.labas.store.util.CompositeKey;

/**
 * Interface for OrderProduct-specific DAO operations.
 */
public interface IOrderProductDAO extends IGenericDAO<OrderProduct, CompositeKey<Long, Long>> {
    // Additional methods for OrderProduct if needed
}
