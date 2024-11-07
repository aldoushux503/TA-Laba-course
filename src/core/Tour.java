package core;

import model.Attraction;
import model.Hotel;

import java.util.ArrayList;
import java.util.List;

/**
 * Abstract class representing a tour
 * This class serves as a base for other tour-related entities.
 * <p>
 * Many-to-Many relationship with hotels, attractions, transports;
 */
public abstract class Tour {
    private String name;
    private String description;
    private double price;
    private List<Hotel> hotels;
    private List<Attraction> attractions;
    private List<Transport> transports;

    public Tour(String name, String description, double price) {
        this.name = name;
        this.description = description;
        this.price = price;
        this.hotels = new ArrayList<>();
        this.attractions = new ArrayList<>();
        this.transports = new ArrayList<>();
    }

    public void addHotel(Hotel hotel) {
        hotels.add(hotel);
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void addTransport(Transport transport) {
        transports.add(transport);
    }

    public abstract void displayTourDetails();
}
