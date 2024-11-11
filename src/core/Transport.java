package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a transport
 * This class serves as a base for other transport-related entities.
 * Many-to-Many relationship with Tours;
 */
public abstract class Transport extends Priceable {
    private String model;
    private List<Tour> tours;

    public Transport(long id, double price, String model) {
        super(id, price);
        this.model = model;
    }

    public Transport(long id, double price, String model, List<Tour> tours) {
        super(id, price);
        this.model = model;
        this.tours = new ArrayList<>(tours);
    }

    public void addTour(Tour tour) {
        tours.add(tour);
        tour.addTransport(this);
    }
    public void addTour(List<Tour> tours) {
        tours = new ArrayList<>(tours);
        tours.forEach(t -> t.addTransport(this));
    }

    public abstract void transportDetails();
}