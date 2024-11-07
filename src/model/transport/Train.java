package model.transport;

import core.Tour;
import core.Transport;

import java.util.List;

public class Train extends Transport {
    private int numberOfCarriages;

    public Train(long id, String name, double cost, int numberOfCarriages) {
        super(id, name, cost);
        this.numberOfCarriages = numberOfCarriages;
    }

    public Train(long id, String name, double cost, List<Tour> tours, int numberOfCarriages) {
        super(id, name, cost, tours);
        this.numberOfCarriages = numberOfCarriages;
    }

    public int getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(int numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }

    @Override
    public void transportDetails() {
        System.out.println("Bus with number of carriages: " + numberOfCarriages);
    }
}
