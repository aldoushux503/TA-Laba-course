package com.labas.store.model.entity;

/**
 * Represents a product included in an order.
 */
public class OrderProduct {
    private Long orderProductId;
    private Float priceAtOrder;
    private Integer quantity;
    private Order order;
    private Product product;

    public OrderProduct() {
    }

    public OrderProduct(Long orderProductId, Float priceAtOrder, Integer quantity, Order order, Product product) {
        this.orderProductId = orderProductId;
        this.priceAtOrder = priceAtOrder;
        this.quantity = quantity;
        this.order = order;
        this.product = product;
    }

    public Long getOrderProductId() {
        return orderProductId;
    }

    public void setOrderProductId(Long orderProductId) {
        this.orderProductId = orderProductId;
    }

    public Float getPriceAtOrder() {
        return priceAtOrder;
    }

    public void setPriceAtOrder(Float priceAtOrder) {
        this.priceAtOrder = priceAtOrder;
    }

    public Integer getQuantity() {
        return quantity;
    }

    public void setQuantity(Integer quantity) {
        this.quantity = quantity;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public Product getProduct() {
        return product;
    }

    public void setProduct(Product product) {
        this.product = product;
    }
}
