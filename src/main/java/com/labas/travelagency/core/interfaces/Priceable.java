package main.java.com.labas.travelagency.core.interfaces;


/**
 * Interface representing entities that have a price attribute.
 * Classes implementing this interface can be priced, with methods to get and set the price.
 */
public interface Priceable {
    double getPrice();
    void setPrice(double price);
}
