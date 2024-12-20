package com.labas.travelagency.model.hotel;

import com.labas.travelagency.core.Entity;
import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.interfaces.Describable;
import com.labas.travelagency.core.interfaces.Rateable;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.hotel.MealPlan;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;


/**
 * Represents a room within a hotel
 * <p>
 * One-to-Many relationship with Room; A hotel belongs to a many room
 */
public class Hotel extends Entity implements Rateable, Describable {
    private String name;
    private String address;
    private String description;
    private Rating rating;

    private List<Room> rooms;

    private List<Tour> tours;
    private MealPlan mealPlan;

    public Hotel(long id, String name, String address, Rating rating, MealPlan mealPlan) {
        super(id);
        this.name = name;
        this.address = address;
        this.setRating(rating);
        this.rooms = Collections.emptyList();
        this.tours = Collections.emptyList();
        this.mealPlan = mealPlan;
    }

    public Hotel(long id, String name, String address, Rating rating,
                 List<Room> rooms, List<Tour> tours, MealPlan mealPlan) {
        super(id);
        this.name = name;
        this.address = address;
        this.setRating(rating);
        this.rooms = Collections.unmodifiableList(rooms);
        this.tours = Collections.unmodifiableList(tours);
        this.mealPlan = mealPlan;
    }

    @Override
    public String getDescription() {
        return description;
    }

    @Override
    public void setDescription(String description) {
        if (description == null || description.isBlank()) {
            throw new IllegalArgumentException("Description cannot be null or empty.");
        }
        this.description = description;
    }

    @Override
    public Rating getRating() {
        return rating;
    }

    @Override
    public void setRating(Rating rating) {
        this.rating = rating;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        if (name == null || name.isBlank()) {
            throw new IllegalArgumentException("Name cannot be null or empty.");
        }
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        if (address == null || address.isBlank()) {
            throw new IllegalArgumentException("Address cannot be null or empty.");
        }
        this.address = address;
    }

    public List<Room> getRooms() {
        return rooms;
    }

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public void setRooms(List<Room> rooms) {
        this.rooms = rooms;
    }

    public MealPlan getMealPlan() {
        return mealPlan;
    }

    public void setMealPlan(MealPlan mealPlan) {
        this.mealPlan = mealPlan;
    }
}
