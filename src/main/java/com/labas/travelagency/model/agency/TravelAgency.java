package main.java.com.labas.travelagency.model.agency;

import main.java.com.labas.travelagency.core.Entity;
import main.java.com.labas.travelagency.core.Tour;

import java.util.ArrayList;
import java.util.List;

public class TravelAgency extends Entity {

    private String name;
    private String address;
    private List<Tour> tours;
    private List<Customer> customers;
    private List<Employee> employees;

    public TravelAgency(long id, String name, String address) {
        super(id);
        this.name = name;
        this.address = address;
        this.tours = new ArrayList<>();
        this.customers = new ArrayList<>();
        this.employees = new ArrayList<>();
    }

    public void addTour(Tour tour) {
        tours.add(tour);
    }

    public void addCustomer(Customer customer) {
        customers.add(customer);
    }

    public void addEmployee(Customer customer) {
        customers.add(customer);
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

    public List<Tour> getTours() {
        return tours;
    }

    public void setTours(List<Tour> tours) {
        this.tours = tours;
    }

    public List<Customer> getCustomers() {
        return customers;
    }

    public void setCustomers(List<Customer> customers) {
        this.customers = customers;
    }

    public List<Employee> getEmployees() {
        return employees;
    }

    public void setEmployees(List<Employee> employees) {
        this.employees = employees;
    }
}
