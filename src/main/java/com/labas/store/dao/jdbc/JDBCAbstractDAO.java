package com.labas.store.dao.jdbc;

import com.labas.store.dao.IGenericDAO;
import com.labas.store.connection.JDBCConnectionPool;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.SQLException;

public abstract class JDBCAbstractDAO<T, ID> implements IGenericDAO<T, ID> {
    private static final Logger logger = LoggerFactory.getLogger(JDBCAbstractDAO.class);
    protected JDBCConnectionPool instance;

    protected JDBCAbstractDAO() {
        try {
            this.instance = JDBCConnectionPool.getInstance();
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