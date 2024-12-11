package com.labas.travelagency.model.transport;

import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.transport.TransportationMode;

import java.util.List;

public class Bus extends Transport {
    private int numberOfDecks;


    public Bus(long id, double price, String model, String description, String seatNumber,
               boolean available, TransportationMode transportMode, Rating rating, int numberOfDecks) {
        super(id, price, model, description, seatNumber, available, transportMode, rating);
        this.numberOfDecks = numberOfDecks;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }
}
