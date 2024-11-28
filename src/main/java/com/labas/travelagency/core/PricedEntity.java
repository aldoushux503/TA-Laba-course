package com.labas.travelagency.core;



/**
 * Abstract class representing an entity with pricing capabilities.
 * Extends the Entity class and implements the Priceable interface.
 * Provides common logic for managing price attributes of derived classes.
 */
public abstract class PricedEntity extends Entity{
    private double price;

    public PricedEntity(long id, double price) {
        super(id);
        this.price = price;
    }


    public double getPrice() {
        return price;
    }


    public void setPrice(double price) {
        this.price = price;
    }
}
