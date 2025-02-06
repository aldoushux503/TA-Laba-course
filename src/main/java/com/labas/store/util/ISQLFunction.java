package com.labas.store.util;

import java.sql.SQLException;

@FunctionalInterface
public interface ISQLFunction<T, R> {
    R apply(T t) throws SQLException;
}