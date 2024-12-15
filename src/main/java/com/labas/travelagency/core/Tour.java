package com.labas.travelagency.core;

import com.labas.travelagency.core.interfaces.Describable;
import com.labas.travelagency.core.interfaces.Rateable;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.general.TravelPurpose;
import com.labas.travelagency.model.hotel.Room;
import com.labas.travelagency.model.tour.Attraction;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Abstract class representing a tour
 * This class serves as a base for other tour-related entities.
 * <p>
 * Many-to-One relationship with room
 * Many-to-Many relationship with attractions, transports;
 */
public abstract class Tour extends Entity implements Rateable, Describable {
    private String name;
    private String description;
    private Rating rating;
    private List<Room> rooms = new ArrayList<>();
    private List<Attraction> attractions = new ArrayList<>();
    private List<Transport> transports = new ArrayList<>();

    private TravelPurpose travelPurpose;

    public Tour(long id, String name, String description, TravelPurpose travelPurpose) {
        super(id);
        this.name = name;
        this.description = description;
        this.travelPurpose = travelPurpose;
    }

    public List<Transport> filterTransportsByRating(double minRating) {
        return transports.stream()
                .filter(attraction -> attraction.getRating().getScore() >= minRating)
                .collect(Collectors.toList());
    }

    public Optional<Transport> getMostPopularTransport() {
        return transports.stream()
                .collect(Collectors.groupingBy(transport -> transport, Collectors.counting()))
                .entrySet()
                .stream()
                .max(Map.Entry.comparingByValue())
                .map(Map.Entry::getKey);
    }


    @Override
    public Rating getRating() {
        return rating;
    }

    @Override
    public void setRating(Rating rating) {
        this.rating = rating;
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

    public TravelPurpose getTravelPurpose() {
        return travelPurpose;
    }

    public void setTravelPurpose(TravelPurpose travelPurpose) {
        this.travelPurpose = travelPurpose;
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
