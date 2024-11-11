package model.tour;

import core.Entity;
import core.Priceable;
import core.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents attractions included in the tours
 * Many-to-Many relationship with Tours;
 */
public class Attraction extends Priceable {
    private String name;
    private String location;
    private String description;
    private List<Tour> tours;

    public Attraction(long id,  double price, String name, String location, String description) {
        super(id, price);
        this.name = name;
        this.location = location;
        this.description = description;
        this.tours = new ArrayList<>();
    }

    public Attraction(long id, double price, String name, String location, String description, List<Tour> tours) {
        super(id, price);
        this.name = name;
        this.location = location;
        this.description = description;
        this.tours = new ArrayList<>(tours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }


    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }
}
