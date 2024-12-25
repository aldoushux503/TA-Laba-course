package com.labas.travelagency.exceptions;

public class CustomerNotFoundException extends TravelAgencyException {
    public CustomerNotFoundException(String message) {
        super(message);
    }

    public CustomerNotFoundException(String message, Throwable cause) {
        super(message, cause);
    }
}
