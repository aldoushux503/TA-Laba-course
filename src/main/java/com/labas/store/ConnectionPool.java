package com.labas.store;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverAction;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.LinkedBlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static final String URL = "jdbc:mysql://localhost:3306/OnlineStore";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    private static final int POOL_SIZE = 10;


    private static volatile ConnectionPool instance;
    private final BlockingQueue<Connection> pool;
    private ConnectionPool() {
        pool = new ArrayBlockingQueue<>(POOL_SIZE);
        for (int i = 0; i < POOL_SIZE; i++) {
            pool.add(createConnection());
        }
    }

    private Connection createConnection() {
        try {
            return DriverManager.getConnection(URL, USER, PASSWORD);
        } catch (SQLException e) {
            LOGGER.warn("Error while creating the connection.", e);

            throw new RuntimeException("Unexpected error: failed to create connection.");
        }
    }


    public static ConnectionPool getInstance() throws SQLException {
        if (instance == null) {
            synchronized (ConnectionPool.class) {
                if (instance == null) {
                    instance = new ConnectionPool();
                }
            }
        }
        return instance;
    }

    public Connection acquiringConnection() throws InterruptedException {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            // Restore the interrupt status to ensure proper thread behavior
            Thread.currentThread().interrupt();

            LOGGER.warn("Attempt to acquire connection was interrupted.", e);

            throw e;
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                if (!connection.isClosed()) {
                    pool.offer(connection);
                } else {
                    LOGGER.warn("Attempted to release a closed connection back to the pool.");
                }
            } catch (SQLException e) {
                LOGGER.error("Error while checking if the connection is closed.", e);
            }
        }
    }
}
