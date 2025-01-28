package com.labas.store.dao;

import com.labas.store.model.entity.OrderProduct;
import com.labas.store.util.CompositeKey;

/**
 * Interface for OrderProduct-specific DAO operations.
 */
public interface OrderProductDAO extends GenericDAO<OrderProduct, CompositeKey<Long, Long>> {
    // Additional methods for OrderProduct if needed
}
