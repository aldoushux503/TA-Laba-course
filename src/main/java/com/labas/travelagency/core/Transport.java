package main.java.com.labas.travelagency.core;

import main.java.com.labas.travelagency.core.interfaces.Priceable;

import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a transport
 * This class serves as a base for other transport-related entities.
 * Many-to-Many relationship with Tours;
 */
public abstract class Transport extends PricedEntity {
    private String model;

    public Transport(long id, double price, String model) {
        super(id, price);
        this.model = model;
    }

    public Transport(long id, double price, String model, List<Tour> tours) {
        super(id, price);
        this.model = model;
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }


    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Transport)) return false;
        Transport o = (Transport) obj;

        return getId() == o.getId() &&
                model.equals(o.model);
    }

    public int hashCode() {
        return Objects.hash(
                getId(),
                model
        );
    }

    @Override
    public String toString() {
        return String.format("Transport model - %s", model);
    }
}