package model;

import core.Entity;

/**
 * Represents a room within a hotel
 * <p>
 * Many-to-One relationship with Hotel; A room belongs to a single hotel
 */
public class Room extends Entity {

    private String number; // Room number can be - 10A, 123Q
    private RoomType type;
    private boolean isAvailable;
    private Hotel hotel;

    public Room(long id, String number, boolean isAvailable, Hotel hotel) {
        super(id);
        this.number = number;
        this.isAvailable = isAvailable;
        this.hotel = hotel;
    }

    public RoomType getType() {
        return type;
    }

    public void setType(RoomType type) {
        this.type = type;
    }

    public String getNumber() {
        return number;
    }

    public void setNumber(String number) {
        this.number = number;
    }

    public boolean isAvailable() {
        return isAvailable;
    }

    public void setAvailable(boolean available) {
        isAvailable = available;
    }

    public Hotel getHotel() {
        return hotel;
    }

    public void setHotel(Hotel hotel) {
        this.hotel = hotel;
    }
}
