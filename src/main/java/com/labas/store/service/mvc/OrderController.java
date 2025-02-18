package com.labas.store.service.mvc;

import com.labas.store.model.dto.OrderDTO;
import com.labas.store.model.entities.Order;
import com.labas.store.model.entities.OrderStatus;
import com.labas.store.model.entities.User;
import com.labas.store.model.entities.builder.OrderBuilder;
import com.labas.store.service.listener.OrderEventManager;
import com.labas.store.service.listener.OrderLoggerListener;

import javax.xml.bind.ValidationException;
import java.util.Optional;

public class OrderController {
    private final OrderModel model;
    private final OrderView view;
    private final OrderEventManager eventManager;

    public OrderController(OrderModel model, OrderView view, OrderEventManager eventManager) {
        this.model = model;
        this.view = view;
        this.eventManager = eventManager;
        setupEventListeners();
    }

    private void setupEventListeners() {
        eventManager.subscribe(new OrderLoggerListener());
    }

    public boolean createOrder(User user, OrderStatus status, Order order) {
        Order newOrder = new OrderBuilder()
                .withOrderId(order.getOrderId())
                .withDiscount(order.getDiscount())
                .withUser(user)
                .withStatus(status)
                .build();

        view.showOrderCreatedConfirmation(newOrder);
        eventManager.notifyOrderCreated(newOrder);

        return newOrder != null;
    }

    public void viewOrder(Long orderId) {
        model.getOrderById(orderId).ifPresentOrElse(
                view::showOrderDetails,
                () -> view.showError("Order not found with ID: " + orderId)
        );
    }
}