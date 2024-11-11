package model.transport;

import core.Tour;
import core.Transport;

import java.util.List;

public class Bus extends Transport {
    private int capacity;


    public Bus(long id, double cost, String name,  int capacity) {
        super(id, cost, name);
        this.capacity = capacity;
    }

    public Bus(long id, double cost, String name, List<Tour> tours, int capacity) {
        super(id, cost, name, tours);
        this.capacity = capacity;
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
