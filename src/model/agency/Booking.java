package model.agency;

import core.Entity;
import core.Tour;
import manager.BookingPriceManager;

import java.time.LocalDate;

/**
 * Represents a booking made by customer
 * <p>
 * One-to-Many relationship with Customer and Tour;
 */
public class Booking extends Entity {
    private Customer customer;
    private Tour tour;
    private LocalDate date;
    private double price;

    public Booking(long id, Customer customer, Tour tour, LocalDate date, double price) {
        super(id);
        this.customer = customer;
        this.tour = tour;
        this.date = date;
        this.price = BookingPriceManager.calculateTourPrice(tour);
    }
}
