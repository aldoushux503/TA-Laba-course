package main.java.com.labas.travelagency.core;

import main.java.com.labas.travelagency.core.interfaces.*;

import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a transport
 * This class serves as a base for other transport-related entities.
 * Many-to-Many relationship with Tours;
 */
public abstract class Transport extends PricedEntity implements Manageable, Describable, Rateable {
    private String model;
    private String description;
    private double rating;
    private boolean available;

    public Transport(long id, double price, String model) {
        super(id, price);
        this.model = model;
    }

    public Transport(long id, double price, String model, List<Tour> tours) {
        super(id, price);
        this.model = model;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void book() {
        if (available) {
            available = false;
            System.out.println("Transport booked successfully.");
        } else {
            System.out.println("Transport is not available for booking.");
        }
    }

    @Override
    public void cancel() {
        System.out.println("Transport booking cancelled.");
        available= true;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {

    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Transport)) return false;
        Transport o = (Transport) obj;

        return getId() == o.getId() &&
                model.equals(o.model);
    }

    public int hashCode() {
        return Objects.hash(
                getId(),
                model
        );
    }

    @Override
    public String toString() {
        return String.format("Transport model - %s", model);
    }
}