package com.labas.store.dao.mysql;

import com.labas.store.dao.IGenericDAO;
import com.labas.store.connection.JDBCConnectionPool;
import com.labas.store.exception.DAOException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Function;

public abstract class MySQLAbstractDAO<T, ID> implements IGenericDAO<T, ID> {
    private static final Logger logger = LoggerFactory.getLogger(MySQLAbstractDAO.class);
    protected JDBCConnectionPool instance;

    protected MySQLAbstractDAO() {
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


    protected Optional<T> findById(String sql, ID id, Function<ResultSet, T> mapper)  {
        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, id);
                try (ResultSet resultSet = statement.executeQuery()) {
                    if (resultSet.next()) {
                        return Optional.of(mapper.apply(resultSet));
                    }
                }
            }
            return Optional.empty();
        } catch (SQLException e) {
            logger.error("Error finding entity by ID: {}", id, e);
            throw new RuntimeException(new DAOException("Error finding entity by ID: " + id, e));
        } finally {
            releaseConnection(connection);
        }
    }


    protected List<T> findAll(String sql, Function<ResultSet, T> mapper) {
        Connection connection = null;
        List<T> entities = new ArrayList<>();
        try {
            connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql);
                 ResultSet resultSet = statement.executeQuery()) {
                while (resultSet.next()) {
                    entities.add(mapper.apply(resultSet));
                }
            }
            return entities;
        } catch (SQLException e) {
            logger.error("Error finding all entities", e);
            throw new RuntimeException(new DAOException("Error finding all entities", e));
        } finally {
            releaseConnection(connection);
        }
    }


    protected boolean save(String sql, BiConsumer<PreparedStatement, T> setter, T entity)  {
        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                setter.accept(statement, entity);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logger.error("Error saving entity: {}", entity, e);
            throw new RuntimeException(new DAOException("Error saving entity: " + entity, e));
        } finally {
            releaseConnection(connection);
        }
    }


    protected boolean update(String sql, BiConsumer<PreparedStatement, T> setter, T entity)  {
        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                setter.accept(statement, entity);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logger.error("Error updating entity: {}", entity, e);
            throw new RuntimeException(new DAOException("Error updating entity: " + entity, e));
        } finally {
            releaseConnection(connection);
        }
    }


    protected boolean delete(String sql, ID id) {
        Connection connection = null;
        try {
            connection = getConnection();
            try (PreparedStatement statement = connection.prepareStatement(sql)) {
                statement.setObject(1, id);
                return statement.executeUpdate() > 0;
            }
        } catch (SQLException e) {
            logger.error("Error deleting entity by ID: {}", id, e);
            throw new RuntimeException(new DAOException("Error deleting entity by ID: " + id, e));
        } finally {
            releaseConnection(connection);
        }
    }
}
