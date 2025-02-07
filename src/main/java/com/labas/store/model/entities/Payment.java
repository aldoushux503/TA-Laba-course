package com.labas.store.model.entities;

import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Represents a payment made by a user for an order.
 */
public class Payment {
    private Long paymentId;
    private LocalDateTime createdAt; // ISO 8601 format preferred
    private LocalDateTime updatedAt; // Nullable
    private PaymentMethod paymentMethod; // Associated payment method
    private User user; // User making the payment
    private Order order; // Associated order

    public Payment() {}

    public Payment(Long paymentId, LocalDateTime createdAt, LocalDateTime updatedAt, PaymentMethod paymentMethod, User user, Order order) {
        this.paymentId = paymentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.paymentMethod = paymentMethod;
        this.user = user;
        this.order = order;
    }

    public Long getPaymentId() {
        return paymentId;
    }

    public void setPaymentId(Long paymentId) {
        this.paymentId = paymentId;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
    }

    public PaymentMethod getPaymentMethod() {
        return paymentMethod;
    }

    public void setPaymentMethod(PaymentMethod paymentMethod) {
        this.paymentMethod = paymentMethod;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
