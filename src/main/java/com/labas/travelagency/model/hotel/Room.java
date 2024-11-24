package main.java.com.labas.travelagency.model.hotel;

import main.java.com.labas.travelagency.core.PricedEntity;
import main.java.com.labas.travelagency.core.interfaces.Manageable;

/**
 * Represents a room within a hotel
 * <p>
 * Many-to-One relationship with Hotel; A room belongs to a single hotel
 */
public class Room extends PricedEntity implements Manageable {

    private String number; // Room number can be - 10A, 123Q
    private RoomType type;
    private boolean available;
    private double price;

    public Room(long id, double price, String number, RoomType type, boolean available) {
        super(id, price);
        this.number = number;
        this.type = type;
        this.available = available;
    }

    @Override
    public boolean isAvailable() {
        return available;
    }

    @Override
    public void book() {
        if (available) {
            available = false;
        } else {
            throw new IllegalStateException("Room is not available for booking.");
        }
    }

    @Override
    public void cancel() {
        if (!available) {
            available = true;
        } else {
            throw new IllegalStateException("Room is already available.");
        }
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

    @Override
    public double getPrice() {
        return price;
    }

    @Override
    public void setPrice(double price) {
        this.price = price;
    }
}
