package com.labas.travelagency.model.transport;

import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.transport.TransportationMode;

import java.util.List;

public class Flight extends Transport {

    private String airline;

    public Flight(long id, double price, String model, String description, String seatNumber,
                  boolean available, TransportationMode transportMode, Rating rating, String airline) {
        super(id, price, model, description, seatNumber, available, transportMode, rating);
        this.airline = airline;
    }

    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
