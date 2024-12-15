package com.labas.travelagency.model.agency;

import com.labas.travelagency.core.Person;

/**
 * Represents a employee entity in the system. Inherits from the Person class
 * This class encapsulates employee-specific attributes and behaviors.
 */
public class Employee extends Person {

    private String role;
    private TravelAgency travelAgency;

    public Employee(long id, String name, String email, String role) {
        super(id, name, email);
        this.role = role;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public TravelAgency getTravelAgency() {
        return travelAgency;
    }

    public void setTravelAgency(TravelAgency travelAgency) {
        this.travelAgency = travelAgency;
    }
}
