package com.labas.travelagency.enums.transport;

public enum TransportationMode {
    ECONOMY("Economy", "Basic transport services", 1.0),
    BUSINESS("Business", "Enhanced comfort and amenities", 1.5),
    LUXURY("Luxury", "Premium transport experience", 2.0);

    private final String name;
    private final String description;
    private final double multiplier;

    TransportationMode(String name, String description, double multiplier) {
        this.name = name;
        this.description = description;
        this.multiplier = multiplier;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public double calculateCost(double baseCost) {
        return baseCost * multiplier;
    }
}
