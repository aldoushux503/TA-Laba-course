package model;

import core.Person;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Represents a customer entity in the system. Inherits from the Person class.
 * This class encapsulates customer-specific attributes and behaviors.
 *
 * Many-to-One relationship with Booking;
 */
public class Customer extends Person {

    private List<Booking> booking;


    public Customer(long id, String name, String email, List<Booking> booking) {
        super(id, name, email);
        this.booking = new ArrayList<>(booking);
    }

    @Override
    public String displayInfo() {
        return String.format("Customer - №%d %s %s %s", getId(), getName(), getEmail(), booking.toString());
    }

    public List<Booking> getBooking() {
        return new ArrayList<>(booking);
    }

    public void setBooking(List<Booking> booking) {
        this.booking = new ArrayList<>(booking);
    }
}
