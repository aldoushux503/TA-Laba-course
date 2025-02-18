package com.labas.store.service.listener;

import com.labas.store.model.entities.Order;

public interface IOrderEventListener {
    void onOrderCreated(Order order);
    void onOrderUpdated(Order order);
    void onOrderDeleted(Long orderId);
}

