package main.java.com.labas.travelagency.model.tour;

import main.java.com.labas.travelagency.core.PricedEntity;
import main.java.com.labas.travelagency.core.interfaces.Priceable;
import main.java.com.labas.travelagency.core.Tour;

import java.util.List;

/**
 * Represents attractions included in the tours
 * Many-to-Many relationship with Tours;
 */
public class Attraction extends PricedEntity {
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
