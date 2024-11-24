package main.java.com.labas.travelagency.core.interfaces;

/**
 * Interface representing bookable entities.
 */
public interface Manageable {
    boolean isAvailable();

    void book();
    void cancel();
}
