package main.java.com.labas.travelagency.model.agency;

import main.java.com.labas.travelagency.core.Person;

import java.util.ArrayList;
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

    public Customer(long id, String name, String email) {
        super(id, name, email);
        this.bookings = Collections.emptyList();
    }

    public Customer(long id, String name, String email, List<Booking> bookings) {
        super(id, name, email);
        this.bookings = Collections.unmodifiableList(bookings);
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
