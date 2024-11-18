package main.java.com.labas.travelagency.util;

public final class Constants {
    public static final double DEFAULT_TOUR_TAX = 1.15;

    private Constants() {

    }

    static {
        System.out.println("Constants class loaded with default tax: " + DEFAULT_TOUR_TAX);
    }
}
