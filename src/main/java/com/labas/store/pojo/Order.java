package com.labas.store.pojo;

/**
 * Represents an order placed by a user in the OnlineStore platform.
 * Tracks order details, status, and timestamps.
 */
public class Order {
    private Long orderId;
    private Float discount;
    private Float total;
    private String createdAt;
    private String updatedAt;
    private OrderStatus orderStatus;
    private User user;

    public Order() {
    }

    public Order(Long orderId, Float discount, Float total, String createdAt, String updatedAt, OrderStatus orderStatus, User user) {
        this.orderId = orderId;
        this.discount = discount;
        this.total = total;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.orderStatus = orderStatus;
        this.user = user;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }

    public Float getDiscount() {
        return discount;
    }

    public void setDiscount(Float discount) {
        this.discount = discount;
    }

    public Float getTotal() {
        return total;
    }

    public void setTotal(Float total) {
        this.total = total;
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

    public OrderStatus getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStatus orderStatus) {
        this.orderStatus = orderStatus;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
