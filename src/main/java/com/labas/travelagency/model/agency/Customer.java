package com.labas.travelagency.model.agency;

import com.labas.Exceptions.InsufficientFundsException;
import com.labas.travelagency.core.Person;

import java.util.Collections;
import java.util.List;

/**
 * Represents a customer entity in the system. Inherits from the Person class.
 * This class encapsulates customer-specific attributes and behaviors.
 *
 * Many-to-One relationship with Booking;
 */
public class Customer extends Person {

    private List<Booking> bookings;

    private double balance;

    public Customer(long id, String name, String email, long balance) {
        super(id, name, email);
        this.bookings = Collections.emptyList();
        this.balance = balance;
    }

    public Customer(long id, String name, String email, long balance, List<Booking> bookings) {
        super(id, name, email);
        this.balance = balance;
        this.bookings = Collections.unmodifiableList(bookings);
    }

    public void processPayment(double bookingPrice) throws InsufficientFundsException {
        if (this.getBalance() < bookingPrice) {
            throw new InsufficientFundsException("The customer does not have sufficient funds to complete the booking.");
        }

        this.setBalance(bookingPrice - getBalance());
    }


    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    public void addBooking(Booking booking) {
        if (booking == null) {
            throw new IllegalArgumentException("Booking cannot be null.");
        }
        this.bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return Collections.unmodifiableList(bookings);
    }
}
