package model;

import core.Person;

/**
 * Represents a customer entity in the system. Inherits from the Person class.
 * This class encapsulates customer-specific attributes and behaviors.
 */
public class Customer extends Person {

    private int loyaltyPoints;

    public Customer(long id, String name, String email, int loyaltyPoints) {
        super(id, name, email);
        this.loyaltyPoints = loyaltyPoints;
    }

    @Override
    public String displayInfo() {
        return String.format("Customer - №%d %s %s %d", getId(), getName(), getEmail(), getLoyaltyPoints());
    }

    public int getLoyaltyPoints() {
        return loyaltyPoints;
    }

    public void setLoyaltyPoints(int loyaltyPoints) {
        this.loyaltyPoints = loyaltyPoints;
    }
}
