package com.labas.store.connection;


import java.sql.Connection;

public class PooledConnection implements AutoCloseable {

    private Connection connnection;
    private JDBCConnectionPool pool;

    PooledConnection(Connection connection, JDBCConnectionPool pool) {
        this.connnection = connection;
        this.pool = pool;
    }

    @Override
    public void close() {
        pool.releaseConnection(connnection);
    }
}
