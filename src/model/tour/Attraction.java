package model.tour;

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

    public Attraction(long id,  double price, String name, String location) {
        super(id, price);
        this.name = name;
        this.location = location;
    }

    public Attraction(long id, double price, String name, String location, List<Tour> tours) {
        super(id, price);
        this.name = name;
        this.location = location;
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


}
