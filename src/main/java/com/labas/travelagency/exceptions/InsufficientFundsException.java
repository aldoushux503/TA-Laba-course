package com.labas.travelagency.exceptions;

public class InsufficientFundsException extends TravelAgencyException {
    public InsufficientFundsException(String message) {
        super(message);
    }

    public InsufficientFundsException(String message, Throwable cause) {
        super(message, cause);
    }
}
