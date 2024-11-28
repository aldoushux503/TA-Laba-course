package main.java.com.labas.exceptions;

public class InvalidBookingException extends TravelAgencyException {
    public InvalidBookingException(String message) {
        super(message);
    }

    public InvalidBookingException(String message, Throwable cause) {
        super(message, cause);
    }
}
