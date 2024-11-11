package model.hotel;

import core.Priceable;
import core.Tour;

import java.util.ArrayList;

/**
 * Represents a room within a hotel
 * <p>
 * Many-to-One relationship with Hotel; A room belongs to a single hotel
 */
public class Room extends Priceable {

    private String number; // Room number can be - 10A, 123Q
    private RoomType type;
    private boolean isAvailable;
    private double price;
    private Hotel hotel;

    private Tour tour;

    public Room(long id, double price, String number, RoomType type, boolean isAvailable, Hotel hotel) {
        super(id, price);
        this.number = number;
        this.type = type;
        this.isAvailable = isAvailable;
        this.hotel = hotel;
    }


    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }

    public Tour getTour() {
        return tour;
    }

    public void setTour(Tour tour) {
        this.tour = tour;
    }
}
