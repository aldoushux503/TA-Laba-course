package com.labas.store.service.decorator;

import com.labas.store.exception.ServiceException;
import com.labas.store.model.entities.Order;
import com.labas.store.service.IOrderService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

public class LoggingOrderServiceDecorator implements IOrderService {
    private final IOrderService decoratedOrderService;
    private static final Logger logger = LoggerFactory.getLogger(LoggingOrderServiceDecorator.class);

    public LoggingOrderServiceDecorator(IOrderService orderService) {
        this.decoratedOrderService = orderService;
    }

    @Override
    public boolean save(Order order) {
        try {
            logger.info("Saving order: " + order);
            return decoratedOrderService.save(order);
        } catch (ServiceException e) {
            logger.error("Error saving order: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        try {
            logger.info("Finding order with ID: " + orderId);
            return decoratedOrderService.findById(orderId);
        } catch (ServiceException e) {
            logger.error("Error finding order: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            logger.info("Retrieving all orders");
            return decoratedOrderService.findAll();
        } catch (ServiceException e) {
            logger.error("Error retrieving orders: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean update(Order order) {
        try {
            logger.info("Updating order: " + order);
            return decoratedOrderService.update(order);
        } catch (ServiceException e) {
            logger.error("Error updating order: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long orderId) {
        try {
            logger.info("Deleting order with ID: " + orderId);
            return decoratedOrderService.delete(orderId);
        } catch (ServiceException e) {
            logger.error("Error deleting order: " + e.getMessage());
            return false;
        }
    }
}