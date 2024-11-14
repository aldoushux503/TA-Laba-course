import core.Tour;
import core.Transport;
import model.agency.Booking;
import model.agency.Customer;
import model.agency.TravelAgency;
import model.hotel.Hotel;
import model.hotel.HotelStars;
import model.hotel.Room;
import model.hotel.RoomType;
import model.tour.AdventureTour;
import model.tour.Attraction;
import model.transport.Flight;

import java.time.LocalDate;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        TravelAgency agency = new TravelAgency(0, "Sunny Travel", "1234 Sunshine St.");

        Customer customer = new Customer(0, "John Doe", "john.doe@example.com");
        agency.addCustomer(customer);

        Hotel hotel = new Hotel(0, "Letoh", "Nepal", HotelStars.FIVE);
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


        Booking booking1 = new Booking(0, customer, adventureTour, LocalDate.now());
        customer.addBooking(booking1);

        System.out.println(booking1);
        System.out.println(customer);
        System.out.println(adventureTour.fullInformationPrint());
    }
}