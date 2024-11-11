package core;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a transport
 * This class serves as a base for other transport-related entities.
 * Many-to-Many relationship with Tours;
 */
public abstract class Transport extends Priceable {
    private String model;
    private List<Tour> tours = new ArrayList<>();

    public Transport(long id, double price, String model) {
        super(id, price);
        this.model = model;
    }

    public Transport(long id, double price, String model, List<Tour> tours) {
        super(id, price);
        this.model = model;
        this.tours = new ArrayList<>(tours);
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public List<Tour> getTours() {
        return new ArrayList<>(tours);
    }

    public void setTours(List<Tour> tours) {
        this.tours = new ArrayList<>(tours);
    }

    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Transport)) return false;
        Transport o = (Transport) obj;

        return getId() == o.getId() &&
                model.equals(o.model) &&
                tours.equals(o.tours);
    }

    public int hashCode() {
        return Objects.hash(
                getId(),
                model,
                tours
        );
    }

    @Override
    public String toString() {
        return String.format("Transport model - %s", model);
    }
}