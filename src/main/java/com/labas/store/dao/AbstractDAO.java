package com.labas.store.dao;

import com.labas.store.util.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class AbstractDAO<T, ID> implements GenericDAO<T, ID> {
    private static final Logger logger = LoggerFactory.getLogger(AbstractDAO.class);
    protected ConnectionPool instance;

    protected AbstractDAO() {
        try {
            this.instance = ConnectionPool.getInstance();
        } catch (SQLException e) {
            throw new RuntimeException("Failed to initialize Connection Pool", e);
        }
    }

    protected Connection getConnection() {
        return instance.acquiringConnection();
    }

    protected void releaseConnection(Connection connection) {
        instance.releaseConnection(connection);
    }
}