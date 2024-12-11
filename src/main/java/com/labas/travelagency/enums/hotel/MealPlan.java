package com.labas.travelagency.enums.hotel;

public enum MealPlan {
    NONE("No Meals", "No meal plan included"),
    BREAKFAST("Breakfast", "Breakfast included"),
    ALL_INCLUSIVE("All-Inclusive", "All meals and beverages included");

    private final String name;
    private final String description;


    MealPlan(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}
