package com.labas.store.model.entities;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.labas.store.util.JsonUtils;

import javax.xml.bind.annotation.*;

/**
 * Represents a product included in an order.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class OrderProduct {

    @XmlAttribute(name = "orderProductId")
    private Long orderProductId;
    @XmlElement(name = "priceAtOrder")
    private Float priceAtOrder;

    @XmlElement(name = "quantity")
    private Integer quantity;

    @XmlAttribute(name = "orderId")
    @XmlIDREF
    private Order order;

    @XmlAttribute(name = "productId")
    @XmlIDREF
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

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setProduct(Product product) {
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


    public Product getProduct() {
        return product;
    }


    @Override
    public String toString() {
        try {
            return JsonUtils.getObjectMapper().writeValueAsString(this);
        } catch (JsonProcessingException e) {
            throw new RuntimeException("Error to represent a OrderProduct class as string" + e);
        }
    }
}
