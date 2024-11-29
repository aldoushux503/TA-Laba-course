package com.labas.Exceptions;

public class TravelAgencyException extends Exception {
    public TravelAgencyException(String message) {
        super(message);
    }

    public TravelAgencyException(String message, Throwable cause) {
        super(message, cause);
    }
}
