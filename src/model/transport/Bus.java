package model.transport;

import core.Tour;
import core.Transport;

import java.util.List;

public class Bus extends Transport {
    private int capacity;


    public Bus(long id, String name, double cost, int capacity) {
        super(id, name, cost);
        this.capacity = capacity;
    }

    public Bus(long id, String name, double cost, List<Tour> tours, int capacity) {
        super(id, name, cost, tours);
        this.capacity = capacity;
    }

    @Override
    public void transportDetails() {
        System.out.println("Bus with capacity: " + capacity);
    }

    public int getCapacity() {
        return capacity;
    }

    public void setCapacity(int capacity) {
        this.capacity = capacity;
    }
}
