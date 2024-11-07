package model;

import core.Entity;
import core.Tour;

import java.util.ArrayList;
import java.util.List;

/**
 * Represents attractions included in the tours
 * Many-to-Many relationship with Tours;
 */
public class Attraction extends Entity {
    private String name;
    private String location;
    private String description;

    private List<Tour> tours;

    public Attraction(long id, String name, String location, String description, List<Tour> tours) {
        super(id);
        this.name = name;
        this.location = location;
        this.description = description;
        this.tours = new ArrayList<>(tours);
    }
}
