package com.labas.store.service.listener;


import com.labas.store.model.entities.Order;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class OrderLoggerListener implements IOrderEventListener {
    private static final Logger logger = LoggerFactory.getLogger(OrderLoggerListener.class);

    @Override
    public void onOrderCreated(Order order) {
        logger.info("Order created: " + order);
    }

    @Override
    public void onOrderUpdated(Order order) {
        logger.info("Order updated: " + order);
    }

    @Override
    public void onOrderDeleted(Long orderId) {
        logger.info("Order deleted with ID: " + orderId);
    }
}