package com.labas.store.model.entity;

/**
 * Represents a payment made by a user for an order.
 */
public class Payment {
    private Long paymentId;
    private String createdAt; // ISO 8601 format preferred
    private String updatedAt; // Nullable
    private PaymentMethod paymentMethod; // Associated payment method
    private User user; // User making the payment
    private Order order; // Associated order

    public Payment() {}

    public Payment(Long paymentId, String createdAt, String updatedAt, PaymentMethod paymentMethod, User user, Order order) {
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

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(String updatedAt) {
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
