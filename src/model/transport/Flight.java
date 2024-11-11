package model.transport;

import core.Tour;
import core.Transport;

import java.util.List;

public class Flight extends Transport {

    private String airline;

    public Flight(long id, double cost, String model, String airline) {
        super(id, cost, model);
        this.airline = airline;
    }

    public Flight(long id, double cost, String name, List<Tour> tours, String airline) {
        super(id, cost, name, tours);
        this.airline = airline;
    }


    public String getAirline() {
        return airline;
    }

    public void setAirline(String airline) {
        this.airline = airline;
    }
}
