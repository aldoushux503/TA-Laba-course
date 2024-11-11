package model.transport;

import core.Tour;
import core.Transport;

import java.util.List;

public class Train extends Transport {
    private int numberOfCarriages;

    public Train(long id, double cost, String name,  int numberOfCarriages) {
        super(id, cost, name);
        this.numberOfCarriages = numberOfCarriages;
    }

    public Train(long id, double cost, String name,  List<Tour> tours, int numberOfCarriages) {
        super(id, cost, name, tours);
        this.numberOfCarriages = numberOfCarriages;
    }

    public int getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(int numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }

}
