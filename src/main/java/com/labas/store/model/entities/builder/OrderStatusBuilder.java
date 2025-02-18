package com.labas.store.model.entities.builder;


import com.labas.store.model.entities.OrderStatus;

public class OrderStatusBuilder {
    private Long orderStatusId;
    private String statusName;

    public OrderStatusBuilder withOrderStatusId(Long orderStatusId) {
        this.orderStatusId = orderStatusId;
        return this;
    }

    public OrderStatusBuilder withStatusName(String statusName) {
        this.statusName = statusName;
        return this;
    }

    public OrderStatus build() {
        return new OrderStatus(orderStatusId, statusName);
    }
}