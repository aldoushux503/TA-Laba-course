package com.labas.travelagency;


import com.labas.exceptions.CustomerNotFoundException;
import com.labas.travelagency.core.Tour;
import com.labas.travelagency.core.Transport;
import com.labas.travelagency.enums.general.Rating;
import com.labas.travelagency.enums.general.TravelPurpose;
import com.labas.travelagency.enums.hotel.MealPlan;
import com.labas.travelagency.enums.hotel.RoomType;
import com.labas.travelagency.enums.transport.TransportationMode;
import com.labas.travelagency.model.agency.Booking;
import com.labas.travelagency.model.agency.Customer;
import com.labas.travelagency.model.agency.TravelAgency;
import com.labas.travelagency.model.hotel.Hotel;
import com.labas.travelagency.model.hotel.Room;
import com.labas.travelagency.model.tour.AdventureTour;
import com.labas.travelagency.model.tour.Attraction;
import com.labas.travelagency.model.transport.Flight;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDate;
import java.util.List;

public class Main {

    private static final Logger logger = LoggerFactory.getLogger("Main.class");
    public static void main(String[] args) {

        Customer customer = new Customer(0, "John Doe", "john.doe@example.com", 1000);



        Hotel hotel = new Hotel(0, "Letoh", "Nepal", Rating.EXCELLENT, MealPlan.BREAKFAST);
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
                "Good airplane",
                "A12",
                true,
                TransportationMode.BUSINESS,
                Rating.EXCELLENT,
                "LOT"
        );

        Tour adventureTour = new AdventureTour(
                0,
                "Mountain Hike",
                "Exciting mountain adventure",
                TravelPurpose.EDUCATION,
                "Hard",
                List.of("Tent", "Knife", "Water"),
                true
        );


        adventureTour.addRoom(room);
        adventureTour.addAttraction(attraction);
        adventureTour.addTransport(transport);


        Booking booking = new Booking(0, customer, adventureTour, LocalDate.now());
        customer.addBooking(booking);
        booking.processBooking();

        TravelAgency agency = new TravelAgency(0, "Sunny Travel", "1234 Sunshine St.");
        agency.addCustomer(customer);
        agency.addTour(adventureTour);

        try {
            logger.info(String.valueOf(agency.findCustomerById(0)));
        } catch (CustomerNotFoundException e) {
            logger.error("Customer not found:", e);
        }

        logger.info(String.valueOf(agency.findTourByName("Mountain Hike")));
        logger.info(String.valueOf(agency.findEmployeesByRole("Manager")));
        logger.info(String.valueOf(agency.removeToursByCondition(c -> c.getName().length() > 2)));
        logger.info(String.format("After remove - %s%n",  agency.getTours()));

        logger.info(String.format(String.valueOf(adventureTour.filterTransportsByRating(3))));
        logger.info(String.format(String.valueOf(adventureTour.getMostPopularTransport())));


    }
}