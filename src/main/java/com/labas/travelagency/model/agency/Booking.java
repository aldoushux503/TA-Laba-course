package com.labas.travelagency.model.agency;

import com.labas.travelagency.exceptions.InsufficientFundsException;
import com.labas.travelagency.exceptions.ReservationException;
import com.labas.travelagency.core.Entity;
import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.interfaces.Manageable;
import com.labas.travelagency.manager.BookingPriceManager;
import com.labas.travelagency.manager.strategy.TaxStrategy;
import com.labas.travelagency.util.Constants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

/**
 * Represents a booking made by customer
 * <p>
 * One-to-Many relationship with Customer and Tour;
 */
public class Booking extends Entity {

    private static final Logger logger = LoggerFactory.getLogger("Booking.class");
    private Customer customer;
    private Tour tour;
    private LocalDate date;
    private double price;

    public Booking(long id, Customer customer, Tour tour, LocalDate date) {
        super(id);
        this.customer = customer;
        this.tour = tour;
        this.date = date;
        this.price = BookingPriceManager.calculateTourPrice(tour, Constants.DEFAULT_TAX_STRATEGY);
    }

    public Booking(long id, Customer customer, Tour tour, LocalDate date, TaxStrategy strategy) {
        super(id);
        this.customer = customer;
        this.tour = tour;
        this.date = date;
        this.price = BookingPriceManager.calculateTourPrice(tour, strategy);
    }

    @Override
    public String toString() {
        return String.format(
                "Client %s has a booking to %s total price - %.2f", customer.getFullName(), tour.getName(), price
        );
    }

    public void processBooking() {
        try {
            // Take payment from customer
            Customer customer = this.getCustomer();
            customer.processPayment(this.getPrice());

            // Book rooms and Transport
            reserveResources(this.tour.getRooms(), "Room");
            reserveResources(this.tour.getTransports(), "Transport");

            logger.info("Booking processed successfully for customer: " + customer.getFullName());
        } catch (InsufficientFundsException e) {
            logger.error("Payment failed for booking.", e);
        }
    }

    private <T extends Manageable> void reserveResources(List<T> resources, String resourceType) {
        resources.forEach(r -> {
            try {
                r.reserve();
            } catch (ReservationException e) {
                logger.error("Failed to reserve " + resourceType + ": " + r, e);
            }
        });
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
}
