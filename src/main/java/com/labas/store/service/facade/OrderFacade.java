package com.labas.store.service.facade;

import com.labas.store.exception.ServiceException;
import com.labas.store.model.entities.*;
import com.labas.store.model.entities.builder.OrderBuilder;
import com.labas.store.service.IOrderService;
import com.labas.store.service.IOrderStatusService;
import com.labas.store.service.IUserService;
import com.labas.store.service.decorator.LoggingOrderServiceDecorator;
import com.labas.store.service.listener.OrderEventManager;
import com.labas.store.service.listener.OrderLoggerListener;
import com.labas.store.service.proxy.CachingOrderServiceProxy;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class OrderFacade {
    private final IOrderService orderService;
    private final IUserService userService;
    private final IOrderStatusService orderStatusService;

    private final OrderEventManager eventManager;

    public OrderFacade(IOrderService orderService, IUserService userService,
                       IOrderStatusService orderStatusService, OrderEventManager eventManager) {
        this.orderService = decorateOrderService(orderService);
        this.userService = userService;
        this.orderStatusService = orderStatusService;
        this.eventManager = eventManager;
        setupEventListeners();
    }

    private void setupEventListeners() {
        eventManager.subscribe(new OrderLoggerListener());
    }

    private IOrderService decorateOrderService(IOrderService orderService) {
        return new CachingOrderServiceProxy(new LoggingOrderServiceDecorator(orderService));
    }

    public boolean createOrder(User user, Float discount, Float total, OrderStatus status) {
        try {
            userService.save(user);
            orderStatusService.save(status);
            Order order = new OrderBuilder()
                    .withUser(user)
                    .withDiscount(discount)
                    .withTotal(total)
                    .withStatus(status)
                    .build();
            boolean success = orderService.save(order);
            if (success) {
                eventManager.notifyOrderCreated(order);
            }
            return success;
        } catch (ServiceException e) {
            System.err.println("Error creating order: " + e.getMessage());
            return false;
        }
    }

    public List<Order> getAllOrders() {
        try {
            return orderService.findAll();
        } catch (ServiceException e) {
            System.err.println("Error retrieving orders: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    public Optional<Order> getOrder(Long orderId) {
        try {
            return orderService.findById(orderId);
        } catch (ServiceException e) {
            System.err.println("Error retrieving order: " + e.getMessage());
            return Optional.empty();
        }
    }

    public boolean updateOrder(Order order) {
        try {
            boolean success = orderService.update(order);
            if (success) {
                eventManager.notifyOrderUpdated(order);
            }
            return success;
        } catch (ServiceException e) {
            System.err.println("Error updating order: " + e.getMessage());
            return false;
        }
    }

    public boolean deleteOrder(Long orderId) {
        try {
            boolean success = orderService.delete(orderId);
            if (success) {
                eventManager.notifyOrderDeleted(orderId);
            }
            return success;
        } catch (ServiceException e) {
            System.err.println("Error deleting order: " + e.getMessage());
            return false;
        }
    }
}