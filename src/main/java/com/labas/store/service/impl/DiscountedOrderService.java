package com.labas.store.service.impl;

import com.labas.store.model.entities.Order;
import com.labas.store.service.IOrderService;
import com.labas.store.service.strategy.IDiscountStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

// Integration into Order Service
public class DiscountedOrderService implements IOrderService {
    private final IOrderService orderService;
    private IDiscountStrategy discountStrategy;
    private static final Logger logger = LoggerFactory.getLogger(DiscountedOrderService.class);

    public DiscountedOrderService(IOrderService orderService, IDiscountStrategy discountStrategy) {
        this.orderService = orderService;
        this.discountStrategy = discountStrategy;
    }

    public void setDiscountStrategy(IDiscountStrategy discountStrategy) {
        this.discountStrategy = discountStrategy;
        logger.info("Discount strategy updated: " + discountStrategy.getClass().getSimpleName());
    }

    @Override
    public boolean save(Order order) {
        try {
            order.setTotal(discountStrategy.applyDiscount(order.getTotal()));
            boolean result = orderService.save(order);
            logger.info("Order saved: " + order);
            return result;
        } catch (Exception e) {
            logger.error("Error saving order: " + e.getMessage());
            return false;
        }
    }

    @Override
    public Optional<Order> findById(Long orderId) {
        try {
            return orderService.findById(orderId);
        } catch (Exception e) {
            logger.error("Error finding order: " + e.getMessage());
            return Optional.empty();
        }
    }

    @Override
    public List<Order> findAll() {
        try {
            return orderService.findAll();
        } catch (Exception e) {
            logger.error("Error retrieving all orders: " + e.getMessage());
            return Collections.emptyList();
        }
    }

    @Override
    public boolean update(Order order) {
        try {
            order.setTotal(discountStrategy.applyDiscount(order.getTotal()));
            boolean result = orderService.update(order);
            logger.info("Order updated: " + order);
            return result;
        } catch (Exception e) {
            logger.error("Error updating order: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean delete(Long orderId) {
        try {
            boolean result = orderService.delete(orderId);
            if (result) {
                logger.info("Order deleted with ID: " + orderId);
            }
            return result;
        } catch (Exception e) {
            logger.error("Error deleting order: " + e.getMessage());
            return false;
        }
    }
}