package main.java.com.labas.travelagency.manager;

import main.java.com.labas.travelagency.core.PricedEntity;
import main.java.com.labas.travelagency.core.Tour;
import main.java.com.labas.travelagency.manager.strategy.TaxStrategy;
import main.java.com.labas.travelagency.util.Constants;

import java.util.List;

/**
 * Manager class responsible for calculating the total price of various entities within a tour
 * This class is designed to follow the Single Responsibility Principle (SRP)
 */
public class BookingPriceManager {
    public static double calculateTotalPrice(List<? extends PricedEntity> items) {
        return items.stream()
                .mapToDouble(PricedEntity::getPrice)
                .sum();
    }

    public static double calculateTourPrice(Tour tour, TaxStrategy strategy) {
        double totalPrice = calculateTotalPrice(tour.getRooms())
                + calculateTotalPrice(tour.getAttractions())
                + calculateTotalPrice(tour.getTransports());

        return strategy != null ? strategy.applyTax(totalPrice) : totalPrice;
    }

}
