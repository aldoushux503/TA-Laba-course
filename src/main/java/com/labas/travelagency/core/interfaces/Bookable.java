package main.java.com.labas.travelagency.core.interfaces;

/**
 * Interface representing bookable entities.
 */
public interface Bookable {
    boolean isAvailable();

    void book();
}
