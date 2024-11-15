package main.java.com.labas.travelagency.model.hotel;

import main.java.com.labas.travelagency.core.Entity;
import main.java.com.labas.travelagency.core.Tour;

import java.util.ArrayList;
import java.util.List;


/**
 * Represents a room within a hotel
 * <p>
 * One-to-Many relationship with Room; A hotel belongs to a many room
 */
public class Hotel extends Entity {
    private String name;
    private String address;
    private HotelStars stars;
    private List<Room> rooms = new ArrayList<>();

    private List<Tour> tours = new ArrayList<>();

    public Hotel(long id, String name, String address, HotelStars stars) {
        super(id);
        this.name = name;
        this.address = address;
        this.stars = stars;
    }

    public Hotel(long id, String name, String address, HotelStars stars, List<Room> rooms, List<Tour> tours) {
        super(id);
        this.name = name;
        this.address = address;
        this.stars = stars;
        this.rooms = new ArrayList<>(rooms);
        this.tours = new ArrayList<>(tours);
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public HotelStars getStars() {
        return stars;
    }

    public void setStars(HotelStars stars) {
        this.stars = stars;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
