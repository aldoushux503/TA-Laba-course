package main.java.com.labas.travelagency.core;

/**
 * Abstract class representing an entity that has both an id and a price
 */
public abstract class Priceable extends Entity{

    private double price;

    public Priceable(long id, double price) {
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
