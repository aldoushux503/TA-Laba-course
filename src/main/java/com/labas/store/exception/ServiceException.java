package com.labas.store.exception;

/**
 * Exception class for service layer errors.
 */
public class ServiceException extends Exception {
    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}