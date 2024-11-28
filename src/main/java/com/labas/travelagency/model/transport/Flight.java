package com.labas.travelagency.model.transport;

import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;

import java.util.List;

public class Flight extends Transport {

    private String airline;

    public Flight(long id, double cost, String model, String seatNumber, String airline) {
        super(id, cost, model, seatNumber);
        this.airline = airline;
    }

    public Flight(long id, double cost, String name, String seatNumber, List<Tour> tours, String airline) {
        super(id, cost, name, seatNumber, tours);
        this.airline = airline;
    }


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
