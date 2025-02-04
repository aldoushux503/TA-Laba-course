package com.labas.store.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class ConnectionPool {
    private static final Logger LOGGER = LoggerFactory.getLogger(ConnectionPool.class);
    private static final String URL = "jdbc:mysql://localhost:3306/OnlineStore";
    private static final String USER = "root";
    private static final String PASSWORD = "7415";
    private static final int POOL_SIZE = 1;


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
            System.out.println();
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

    public Connection acquiringConnection() {
        try {
            return pool.take();
        } catch (InterruptedException e) {
            LOGGER.warn("Attempt to acquire connection was interrupted.", e);

            // Restore the interrupt status to ensure proper thread behavior
            Thread.currentThread().interrupt();

            throw new RuntimeException("Failed to retrieve connection", e);
        }
    }

    public void releaseConnection(Connection connection) {
        if (connection != null) {
            try {
                pool.put(connection);
            } catch (InterruptedException e) {
                LOGGER.error("Error while releasing connection back to the pool", e);
                Thread.currentThread().interrupt();
            }
        }
    }
}
