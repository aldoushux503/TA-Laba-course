package com.labas.travelagency.enums.hotel;

public enum RoomType {
    SINGLE(1, "Single Room", 50.0),
    DOUBLE(2, "Double Room", 90.0),
    SUITE(4, "Suite", 200.0);

    private final int capacity;
    private final String description;
    private final double basePrice;

    RoomType(int capacity, String description, double basePrice) {
        this.capacity = capacity;
        this.description = description;
        this.basePrice = basePrice;
    }

    public int getCapacity() {
        return capacity;
    }

    public String getDescription() {
        return description;
    }

    public double getBasePrice() {
        return basePrice;
    }
}


