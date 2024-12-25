package com.labas.travelagency.model.agency;

import com.labas.travelagency.exceptions.CustomerNotFoundException;
import com.labas.travelagency.core.Entity;
import com.labas.travelagency.core.Tour;

import java.util.*;
import java.util.function.Predicate;
import java.util.stream.Collectors;

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

    public void addEmployee(Employee employee) {
        if (!employees.contains(employee)) {
            employees.add(employee);
        } else {
            throw new IllegalArgumentException("Employee already exists in the agency.");
        }
    }

    public Customer findCustomerById(long customerId) throws CustomerNotFoundException {
        Customer customer = customers.stream()
                .filter(c -> c.getId() == customerId)
                .findFirst()
                .orElse(null);
        if (customer == null) {
            throw new CustomerNotFoundException("Customer with ID " + customerId + " not found.");
        }
        return customer;
    }
    public List<Employee> findEmployeesByRole(String role) {
        return employees.stream()
                .filter(employee -> employee.getRole().equalsIgnoreCase(role))
                .collect(Collectors.toList());
    }

    public Optional<Tour> findTourByName(String name) {
        return tours.stream()
                .filter(tour -> tour.getName().equalsIgnoreCase(name))
                .findFirst();
    }

    public boolean removeToursByCondition(Predicate<Tour> condition) {
        return tours.removeIf(condition);
    }

    public void addCustomer(Customer customer) {
        if (!customers.contains(customer)) {
            customers.add(customer);
        } else {
            throw new IllegalArgumentException("Customer already exists in the agency.");
        }
    }

    public void addTour(Tour tour) {
        if (!tours.contains(tour)) {
            tours.add(tour);
        } else {
            throw new IllegalArgumentException("Tour already exists in the agency.");
        }
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
