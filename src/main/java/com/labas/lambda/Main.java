package com.labas.lambda;

import com.labas.travelagency.core.Person;
import com.labas.travelagency.model.agency.Booking;
import com.labas.travelagency.model.agency.Customer;

import java.util.*;
import java.util.function.*;
import java.util.stream.Collectors;

import java.util.List;
import java.util.Map;
import java.util.function.Consumer;
import java.util.stream.Collectors;

public class Main {
    public static void main(String[] args) {
        List<Customer> customers = List.of(
                new Customer(1, "Alice", "alice@mail.com", 15000.0),
                new Customer(2, "Bob", "bob@mail.com", 5000.0),
                new Customer(3, "Charlie", "charlie@mail.com", 20000.0),
                new Customer(4, "David", "david@mail.com", 100.0)
        );

        // 1. Predicate: Filter customers with a balance > 1000
        Predicate<Customer> balancePredicate = customer -> customer.getBalance() > 1000;
        List<Customer> richCustomers = customers.stream()
                .filter(balancePredicate)
                .toList();

        // 2. Function: Transform customer names to uppercase
        Function<Customer, Customer> toUppercaseName = customer ->
                new Customer(customer.getId(), customer.getFullName().toUpperCase(),
                        customer.getEmail(), customer.getBalance(), customer.getBookings());

        List<Customer> uppercaseNameCustomers = richCustomers.stream()
                .map(toUppercaseName)
                .toList();

        // 3. ToDoubleFunction: Extract the balance of customers
        ToDoubleFunction<Customer> balanceExtractor = Customer::getBalance;
        double totalBalance = customers.stream()
                .mapToDouble(balanceExtractor)
                .sum();

        // 4. Function: Group customers by the first letter of their name
        Function<Customer, Character> groupByFirstLetter = customer -> customer.getFullName().charAt(0);
        Map<Character, List<Customer>> customersByLetter = customers.stream()
                .collect(Collectors.groupingBy(groupByFirstLetter));

        // 5. Consumer: Print customer information
        Consumer<Customer> printCustomer = customer -> System.out.println(
                customer.getFullName() + " - Balance: " + customer.getBalance());

        // Output Results
        System.out.println("Customers with a balance > 1000 (names in uppercase):");
        uppercaseNameCustomers.forEach(printCustomer);

        System.out.println("\nGrouping customers by the first letter of their name:");
        customersByLetter.forEach((letter, group) -> {
            System.out.println(letter + ": " + group);
        });

        System.out.println("\nTotal balance of all customers: " + totalBalance);
    }
}

