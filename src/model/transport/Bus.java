package model.transport;

import core.Tour;
import core.Transport;

import java.util.List;

public class Bus extends Transport {
    private int numberOfDecks;


    public Bus(long id, double cost, String model,  int numberOfDecks) {
        super(id, cost, model);
        this.numberOfDecks = numberOfDecks;
    }

    public Bus(long id, double cost, String name, List<Tour> tours, int numberOfDecks) {
        super(id, cost, name, tours);
        this.numberOfDecks = numberOfDecks;
    }

    public int getNumberOfDecks() {
        return numberOfDecks;
    }

    public void setNumberOfDecks(int numberOfDecks) {
        this.numberOfDecks = numberOfDecks;
    }
}
