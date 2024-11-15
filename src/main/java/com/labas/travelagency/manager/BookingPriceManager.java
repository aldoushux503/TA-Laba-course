package main.java.com.labas.travelagency.manager;

import main.java.com.labas.travelagency.core.Priceable;
import main.java.com.labas.travelagency.core.Tour;

import java.util.List;

/**
 * Manager class responsible for calculating the total price of various entities within a tour
 * This class is designed to follow the Single Responsibility Principle (SRP)
 */
public class BookingPriceManager {
    public static double calculateTotalPrice(List<? extends Priceable> items) {
        return items.stream()
                .mapToDouble(Priceable::getPrice)
                .sum();
    }

    public static double calculateTourPrice(Tour tour) {
        double hotelPrice = calculateTotalPrice(tour.getRooms());
        double attractionPrice = calculateTotalPrice(tour.getAttractions());
        double transportPrice = calculateTotalPrice(tour.getTransports());

        return hotelPrice + attractionPrice + transportPrice;
    }
}
