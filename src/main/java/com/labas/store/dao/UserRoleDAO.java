package com.labas.store.dao;

import com.labas.store.model.entity.UserRole;
import com.labas.store.util.CompositeKey;

/**
 * Interface for UserRole-specific DAO operations.
 */
public interface UserRoleDAO extends GenericDAO<UserRole, CompositeKey<Long, Long>> {
    // Additional methods for UserRole if needed
}
