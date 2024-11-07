package model;

import core.Entity;
import core.Tour;

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
    private double rating;
    private List<Room> rooms;

    private List<Tour> tours;

    public Hotel(long id, String name, String address, double rating, List<Room> rooms, List<Tour> tours) {
        super(id);
        this.name = name;
        this.address = address;
        this.rating = rating;
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

    public double getRating() {
        return rating;
    }

    public void setRating(double rating) {
        this.rating = rating;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }
}
