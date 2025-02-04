package com.labas.store.connection;

import java.sql.Connection;
import java.sql.SQLException;

public interface ConnectionProvider {
    Connection acquiringConnection() throws SQLException;
    void releaseConnection(Connection connection);
}