import core.Tour;
import model.agency.Booking;
import model.agency.Customer;
import model.agency.TravelAgency;
import model.tour.AdventureTour;

import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        TravelAgency agency = new TravelAgency(0, "Sunny Travel", "1234 Sunshine St.");

        Customer customer1 = new Customer(0, "John Doe", "john.doe@example.com");
        agency.addCustomer(customer1);

        Tour adventureTour = new AdventureTour(0, "Mountain Hike", "Exciting mountain adventure", "Hard");
        agency.addTour(adventureTour);

        Booking booking1 = new Booking(0, customer1, adventureTour, LocalDate.now());
        customer1.addBooking(booking1);

        System.out.println(booking1);
    }
}