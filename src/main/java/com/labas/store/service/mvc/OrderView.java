package com.labas.store.service.mvc;

import com.labas.store.model.entities.Order;

public interface OrderView {
    void showOrderDetails(Order order);
    void showError(String message);
    void showOrderCreatedConfirmation(Order order);
}
