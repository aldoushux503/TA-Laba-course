package com.labas.store.service.mvc;

import com.labas.store.model.entities.Order;

public class ConsoleOrderView implements OrderView {
    @Override
    public void showOrderDetails(Order order) {
        System.out.println("\n=== Order Details ===");
        System.out.printf("ID: %d\nUser: %s\nTotal: $%.2f\nStatus: %s\n",
                order.getOrderId(),
                order.getUser().getFirstName(),
                order.getTotal(),
                order.getOrderStatus().getStatusName());
    }

    @Override
    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    @Override
    public void showOrderCreatedConfirmation(Order order) {
        System.out.printf("Order #%d created successfully!\n", order.getOrderId());
    }
}
