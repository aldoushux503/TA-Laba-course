package model;

import core.Tour;
import core.Transport;

import java.util.List;

public class Flight extends Transport {

    private String airline;

    public Flight(long id, String name, double cost, String airline) {
        super(id, name, cost);
        this.airline = airline;
    }

    public Flight(long id, String name, double cost, List<Tour> tours, String airline) {
        super(id, name, cost, tours);
        this.airline = airline;
    }

    @Override
    public void transportDetails() {
        System.out.println("Flight with airline: " + airline);
    }
}
