package core;

import model.tour.Attraction;
import model.hotel.Hotel;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Abstract class representing a tour
 * This class serves as a base for other tour-related entities.
 * <p>
 * Many-to-Many relationship with hotels, attractions, transports;
 */
public abstract class Tour extends Entity {
    private String name;
    private String description;
    private List<Hotel> hotels;
    private List<Attraction> attractions;
    private List<Transport> transports;

    public Tour(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
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
        transport.addTour(this);
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

    public List<Hotel> getHotels() {
        return hotels;
    }

    public void setHotels(List<Hotel> hotels) {
        this.hotels = hotels;
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
                hotels.equals(o.hotels) &&
                attractions.equals(o.attractions) &&
                transports.equals(o.transports);

    }

    @Override
    public int hashCode() {
        return Objects.hash(
                getId(),
                name,
                description,
                new ArrayList<>(hotels),
                new ArrayList<>(attractions),
                new ArrayList<>(transports)
        );
    }
    @Override
    public String toString() {
        return String.format("Tour: %s, description=%s", name, description);
    }
}
