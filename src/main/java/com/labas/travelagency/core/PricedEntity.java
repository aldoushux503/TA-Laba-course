package main.java.com.labas.travelagency.core;


import main.java.com.labas.travelagency.core.interfaces.Priceable;

/**
 * Abstract class representing an entity with pricing capabilities.
 * Extends the Entity class and implements the Priceable interface.
 * Provides common logic for managing price attributes of derived classes.
 */
public abstract class PricedEntity extends Entity implements Priceable {
    private double price;

    public PricedEntity(long id, double price) {
        super(id);
        this.price = price;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
