package com.labas.store.dao;

import com.labas.store.ConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

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

    protected void getConnection() {
        try {
            instance.acquiringConnection();
        } catch (InterruptedException e) {
            logger.error("Thread was interrupted while acquiring a connection.", e);
            throw new RuntimeException("Interrupted while trying to acquire a connection.", e);
        }
    }

    protected void releaseConnection(Connection connection) {
        instance.releaseConnection(connection);
    }
}