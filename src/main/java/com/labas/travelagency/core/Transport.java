package com.labas.travelagency.core;

import com.labas.travelagency.exceptions.ReservationException;
import com.labas.travelagency.core.interfaces.*;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.transport.TransportationMode;

import java.util.Objects;

/**
 * Abstract class representing a transport
 * This class serves as a base for other transport-related entities.
 * Many-to-Many relationship with Tours;
 */
public abstract class Transport extends PricedEntity implements Manageable, Describable, Rateable {
    private String model;
    private String description;
    private String seatNumber;
    private boolean available;
    private TransportationMode transportMode;

    private Rating rating;

    public Transport(long id, double price, String model, String description,
                     String seatNumber, boolean available, TransportationMode transportMode, Rating rating) {
        super(id, transportMode.calculateCost(price));
        this.model = model;
        this.description = description;
        this.seatNumber = seatNumber;
        this.available = available;
        this.transportMode = transportMode;
        this.rating = rating;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void reserve() throws ReservationException {
        if (available) {
            available = false;
        } else {
            throw new ReservationException("Transport is not available for booking.");
        }
    }

    @Override
    public void cancel() throws ReservationException {
        if (!available) {
            available = true;
        } else {
            throw new ReservationException("Transport is already available.");
        }
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        this.description = description;
    }


    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public TransportationMode getTransportMode() {
        return transportMode;
    }

    public void setTransportMode(TransportationMode transportMode) {
        this.transportMode = transportMode;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }

    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    @Override
    public void setRating(Rating rating) {
        this.rating = rating;
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