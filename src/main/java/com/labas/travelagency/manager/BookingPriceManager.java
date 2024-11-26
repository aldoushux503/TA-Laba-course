package main.java.com.labas.travelagency.manager;

import main.java.com.labas.travelagency.core.PricedEntity;
import main.java.com.labas.travelagency.core.Tour;
import main.java.com.labas.travelagency.manager.strategy.DefaultTaxStrategy;
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

    public static double calculateTourPrice(Tour tour) {
        double hotelPrice = calculateTotalPrice(tour.getRooms());
        double attractionPrice = calculateTotalPrice(tour.getAttractions());
        double transportPrice = calculateTotalPrice(tour.getTransports());

        return applyDefaultTax(hotelPrice + attractionPrice + transportPrice);
    }

    public static double applyDefaultTax(double price) {
        return Constants.DEFAULT_TAX_STRATEGY.applyTax(price);
    }
}
