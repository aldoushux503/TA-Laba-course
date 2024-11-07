package model.tour;

import core.Tour;

public class LuxuryTour extends Tour {
    private boolean includesSpa;

    public LuxuryTour(long id, String name, String description, double price, boolean includesSpa) {
        super(id, name, description, price);
        this.includesSpa = includesSpa;
    }

    @Override
    public void displayTourDetails() {
        System.out.println("Luxury Tour: " + getName() + ", Includes Spa: " + includesSpa);
    }
}
