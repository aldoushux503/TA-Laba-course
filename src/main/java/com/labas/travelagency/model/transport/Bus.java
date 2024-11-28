package com.labas.travelagency.model.transport;

import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;

import java.util.List;

public class Bus extends Transport {
    private int numberOfDecks;


    public Bus(long id, double cost, String model,  int numberOfDecks, String seatNumber) {
        super(id, cost, model, seatNumber);
        this.numberOfDecks = numberOfDecks;
    }

    public Bus(long id, double cost, String name, List<Tour> tours, String seatNumber, int numberOfDecks) {
        super(id, cost, name, seatNumber, tours);
        this.numberOfDecks = numberOfDecks;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }
}
