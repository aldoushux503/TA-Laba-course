package main.java.com.labas.travelagency.core;

import main.java.com.labas.travelagency.core.interfaces.Manageable;
import main.java.com.labas.travelagency.core.interfaces.Describable;
import main.java.com.labas.travelagency.core.interfaces.Rateable;
import main.java.com.labas.travelagency.model.hotel.Room;
import main.java.com.labas.travelagency.model.tour.Attraction;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a tour
 * This class serves as a base for other tour-related entities.
 * <p>
 * Many-to-One relationship with room
 * Many-to-Many relationship with attractions, transports;
 */
public abstract class Tour extends Entity implements Rateable, Manageable, Describable {
    protected String name;
    protected String description;
    private double rating;
    private boolean available;
    protected List<Room> rooms = new ArrayList<>();
    protected List<Attraction> attractions = new ArrayList<>();
    protected List<Transport> transports = new ArrayList<>();

    public Tour(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

    @Override
    public double getRating() {
        return rating;
    }

    @Override
    public void setRating(double rating) {
        this.rating = rating;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void book() {
        if (available) {
            available = false;
            System.out.println("Tour booked successfully.");
        } else {
            System.out.println("Tour is not available for booking.");
        }
    }

    @Override
    public void cancel() {
        available = true;
        System.out.println("Tour booking cancelled.");
    }

    public abstract String fullInformationPrint();

    public void addRoom(Room room) {
        rooms.add(room);
    }

    public void addAttraction(Attraction attraction) {
        attractions.add(attraction);
    }

    public void addTransport(Transport transport) {
        transports.add(transport);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public List<Attraction> getAttractions() {
        return attractions;
    }

    public void setAttractions(List<Attraction> attractions) {
        this.attractions = attractions;
    }

    public List<Transport> getTransports() {
        return transports;
    }

    public void setTransports(List<Transport> transports) {
        this.transports = transports;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == this) return true;
        if (!(obj instanceof Tour)) return false;
        Tour o = (Tour) obj;

        return getId() == o.getId() &&
                name.equals(o.name) &&
                description.equals(o.description) &&
                rooms.equals(o.rooms) &&
                attractions.equals(o.attractions) &&
                transports.equals(o.transports);

    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                name,
                description,
                new ArrayList<>(rooms),
                new ArrayList<>(attractions),
                new ArrayList<>(transports)
        );
    }

    @Override
    public String toString() {
        return String.format("Tour: %s, description=%s", name, description);
    }
}