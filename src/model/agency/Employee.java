package model.agency;

import core.Person;

import java.util.List;

/**
 * Represents a employee entity in the system. Inherits from the Person class
 * This class encapsulates employee-specific attributes and behaviors.
 */
public class Employee extends Person {

    private String position;
    private TravelAgency travelAgency;

    public Employee(long id, String name, String email, String position) {
        super(id, name, email);
        this.position = position;
    }

    @Override
    public String displayInfo() {
        return String.format("Employee - №%d, %s, %s, %s", getId(), getName(), getEmail(), getPosition());
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
}
