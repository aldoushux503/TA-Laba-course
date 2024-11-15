package main.java.com.labas.travelagency.model.transport;

import main.java.com.labas.travelagency.core.Tour;
import main.java.com.labas.travelagency.core.Transport;

import java.util.List;

public class Train extends Transport {
    private int numberOfCarriages;

    public Train(long id, double cost, String model,  int numberOfCarriages) {
        super(id, cost, model);
        this.numberOfCarriages = numberOfCarriages;
    }

    public Train(long id, double cost, String model,  List<Tour> tours, int numberOfCarriages) {
        super(id, cost, model, tours);
        this.numberOfCarriages = numberOfCarriages;
    }

    public int getNumberOfCarriages() {
        return numberOfCarriages;
    }

    public void setNumberOfCarriages(int numberOfCarriages) {
        this.numberOfCarriages = numberOfCarriages;
    }

}
