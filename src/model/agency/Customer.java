package model.agency;

import core.Person;

import java.util.ArrayList;
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
        this.bookings = new ArrayList<>();
    }


    public Customer(long id, String name, String email, List<Booking> bookings) {
        super(id, name, email);
        this.bookings = new ArrayList<>(bookings);
    }


    public void addBooking(Booking booking) {
        this.bookings.add(booking);
    }

    public List<Booking> getBookings() {
        return new ArrayList<>(bookings);
    }

    public void setBookings(List<Booking> bookings) {
        this.bookings = new ArrayList<>(bookings);
    }
}
