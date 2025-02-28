package com.labas.store.exception;

/**
 * Exception class for DAO-related errors.
 */
public class DAOException extends Exception {
    public DAOException(String message, Throwable cause) {
        super(message, cause);
    }
}
