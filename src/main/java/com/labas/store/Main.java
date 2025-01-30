package com.labas.store;

import com.labas.store.exception.ServiceException;
import com.labas.store.model.entity.Order;
import com.labas.store.model.entity.OrderStatus;
import com.labas.store.model.entity.User;
import com.labas.store.service.OrderService;
import com.labas.store.service.OrderStatusService;
import com.labas.store.service.UserService;
import com.labas.store.util.ServiceFactory;

import java.util.List;
import java.util.Optional;


public class Main {
    public static void main(String[] args) {
        // Get the service instance for working with orders
        OrderService orderService = ServiceFactory.getOrderService();
        UserService userService = ServiceFactory.getUserService();
        OrderStatusService orderStatusService = ServiceFactory.getOrderStatusService();

        try {
            // 1. Create a new order
            System.out.println("Creating a new order...");
            Order newOrder = new Order();
            newOrder.setDiscount(10.0f);
            newOrder.setTotal(90.0f);
            newOrder.setCreatedAt("2025-01-27 10:00:00");
            newOrder.setUpdatedAt("2025-01-27 10:00:00");

            // Set related entities (OrderStatus and User)
            OrderStatus orderStatus = new OrderStatus(1L, "Pending"); // Example: Order status with ID = 1
            User user = new User(
                    1L,
                    "John",
                    "Doe",
                    "john.doe@example.com",
                    "123456789",
                    "hashedPassword"
            ); // Example: User with ID = 1

            newOrder.setOrderStatus(orderStatus);
            newOrder.setUser(user);

            boolean userSaved = userService.save(user);
            boolean orderStatusSaved = orderStatusService.save(orderStatus);
            boolean orderSaved = orderService.save(newOrder);
            System.out.println("New order saved: " + orderSaved);

            // 2. Retrieve all orders
            System.out.println("\nRetrieving all orders...");
            List<Order> orders = orderService.findAll();
            orders.forEach(order -> System.out.println("Order: " + order));

            // 3. Retrieve an order by ID
            System.out.println("\nRetrieving order with ID = 1...");
            Optional<Order> foundOrder = orderService.findById(1L);
            foundOrder.ifPresent(order -> System.out.println("Found order: " + order));

            // 4. Update an order
            System.out.println("\nUpdating order with ID = 1...");
            foundOrder.ifPresent(order -> {
                order.setDiscount(15.0f); // Change discount
                order.setTotal(85.0f);   // Update total
                try {
                    boolean updated = orderService.update(order);
                    System.out.println("Order update completed: " + updated);
                } catch (ServiceException e) {
                    e.printStackTrace();
                }
            });

            // 5. Delete an order
            System.out.println("\nDeleting order with ID = 1...");
            boolean deleted = orderService.delete(1L);
            System.out.println("Order deletion completed: " + deleted);

        } catch (ServiceException e) {
            System.err.println("An error occurred while working with orders:");
            e.printStackTrace();
        }



    }
}

