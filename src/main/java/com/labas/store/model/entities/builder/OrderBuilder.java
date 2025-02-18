package com.labas.store.model.entities.builder;

import com.labas.store.model.entities.Order;
import com.labas.store.model.entities.OrderStatus;
import com.labas.store.model.entities.User;

import java.time.LocalDateTime;

public class OrderBuilder {
    private Long orderId;
    private Float discount;
    private Float total;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private OrderStatus orderStatus;
    private User user;

    public OrderBuilder withOrderId(Long orderId) {
        this.orderId = orderId;
        return this;
    }

    public OrderBuilder withDiscount(Float discount) {
        this.discount = discount;
        return this;
    }

    public OrderBuilder withTotal(Float total) {
        this.total = total;
        return this;
    }

    public OrderBuilder withCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
        return this;
    }

    public OrderBuilder withUpdatedAt(LocalDateTime updatedAt) {
        this.updatedAt = updatedAt;
        return this;
    }

    public OrderBuilder withUser(User user) {
        this.user = user;
        return this;
    }

    public OrderBuilder withStatus(OrderStatus status) {
        this.orderStatus = status;
        return this;
    }

    public Order build() {
        return new Order(orderId, discount, total,
                createdAt != null ? createdAt : LocalDateTime.now(),
                updatedAt != null ? updatedAt : LocalDateTime.now(),
                orderStatus, user);
    }
}