package core;

import model.hotel.Room;
import model.tour.Attraction;

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
public abstract class Tour extends Entity {
    protected String name;
    protected String description;
    protected List<Room> rooms = new ArrayList<>();
    protected List<Attraction> attractions = new ArrayList<>();
    protected List<Transport> transports = new ArrayList<>();

    public Tour(long id, String name, String description) {
        super(id);
        this.name = name;
        this.description = description;
    }

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
