package com.labas.store.service.mvc;

import com.labas.store.exception.ServiceException;
import com.labas.store.model.dto.OrderDTO;
import com.labas.store.model.entities.Order;
import com.labas.store.model.entities.User;
import com.labas.store.model.entities.builder.OrderBuilder;
import com.labas.store.service.IOrderService;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class OrderModel {
    private final IOrderService orderService;

    public OrderModel(IOrderService orderService) {
        this.orderService = orderService;
    }


    public Optional<Order> createOrder(User user, Order order) {

        Order newOrder = null;
        try {
            newOrder = new OrderBuilder()
                    .withUser(user)
                    .withDiscount(order.getDiscount())
                    .withTotal(order.getTotal())
                    .withStatus(order.getOrderStatus())
                    .build();

            return orderService.save(order) ? Optional.of(order) : Optional.empty();

        } catch (ServiceException e) {
            System.err.println("Error creating order: " + e.getMessage());
            return Optional.ofNullable(newOrder);
        }
    }

    public Optional<Order> getOrderById(Long orderId) {
        try {
            return orderService.findById(orderId);
        } catch (ServiceException e) {
            System.err.println("Error retrieving order: " + e.getMessage());
            return Optional.empty();
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderService.findAll();
        } catch (
                ServiceException e) {
            System.err.println("Error retrieving order: " + e.getMessage());
            return new ArrayList<>();
        }
    }
}
