package com.labas.travelagency;


import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;
import com.labas.travelagency.model.agency.Booking;
import com.labas.travelagency.model.agency.Customer;
import com.labas.travelagency.model.agency.TravelAgency;
import com.labas.travelagency.model.hotel.Hotel;
import com.labas.travelagency.model.hotel.Room;
import com.labas.travelagency.model.hotel.RoomType;
import com.labas.travelagency.model.tour.AdventureTour;
import com.labas.travelagency.model.tour.Attraction;
import com.labas.travelagency.model.transport.Flight;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TravelAgency agency = new TravelAgency(0, "Sunny Travel", "1234 Sunshine St.");

        Customer customer = new Customer(0, "John Doe", "john.doe@example.com", 1000);
        agency.addCustomer(customer);


        Hotel hotel = new Hotel(0, "Letoh", "Nepal", 4.5);
        Room room = new Room(0, 300, "10A", RoomType.SINGLE, true);
        Room room1 = new Room(1, 300, "101C", RoomType.DOUBLE, true);
        hotel.setRooms(
                List.of(room,
                        room1)
        );

        Attraction attraction = new Attraction(
                0,
                100,
                "Bungee jumping",
                "Kushma"
        );

        Transport transport = new Flight(
                0,
                100,
                "Boeing123",
                "A12",
                "LOT"
        );

        Tour adventureTour = new AdventureTour(
                0,
                "Mountain Hike",
                "Exciting mountain adventure",
                "Hard",
                List.of("Tent", "Knife", "Water"),
                true
        );


        adventureTour.addRoom(room);
        adventureTour.addAttraction(attraction);
        adventureTour.addTransport(transport);


        Booking booking = new Booking(0, customer, adventureTour, LocalDate.now());
        customer.addBooking(booking);
    }
}