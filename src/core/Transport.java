package core;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a transport
 * This class serves as a base for other transport-related entities.
 * Many-to-Many relationship with Tours;
 */
public abstract class Transport extends Entity {
    private String name;
    private double cost;

    private List<Tour> tours;

    public Transport(long id, String name, double cost) {
        super(id);
        this.name = name;
        this.cost = cost;
    }

    public Transport(long id, String name, double cost, List<Tour> tours) {
        super(id);
        this.name = name;
        this.cost = cost;
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