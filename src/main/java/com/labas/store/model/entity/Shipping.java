package com.labas.store.model.entity;

/**
 * Represents a shipping entry in the OnlineStore platform.
 */
public class Shipping {
    private Long shippingId;
    private String shippingCarrier;
    private String trackingNumber;
    private String shippedAt;
    private String estimatedDelivery;
    private ShippingStatus shippingStatus;
    private Order order;

    public Shipping() {}

    public Shipping(Long shippingId, String shippingCarrier, String trackingNumber, String shippedAt, String estimatedDelivery, ShippingStatus shippingStatus, Order order) {
        this.shippingId = shippingId;
        this.shippingCarrier = shippingCarrier;
        this.trackingNumber = trackingNumber;
        this.shippedAt = shippedAt;
        this.estimatedDelivery = estimatedDelivery;
        this.shippingStatus = shippingStatus;
        this.order = order;
    }

    public Long getShippingId() {
        return shippingId;
    }

    public void setShippingId(Long shippingId) {
        this.shippingId = shippingId;
    }

    public String getShippingCarrier() {
        return shippingCarrier;
    }

    public void setShippingCarrier(String shippingCarrier) {
        this.shippingCarrier = shippingCarrier;
    }

    public String getTrackingNumber() {
        return trackingNumber;
    }

    public void setTrackingNumber(String trackingNumber) {
        this.trackingNumber = trackingNumber;
    }

    public String getShippedAt() {
        return shippedAt;
    }

    public void setShippedAt(String shippedAt) {
        this.shippedAt = shippedAt;
    }

    public String getEstimatedDelivery() {
        return estimatedDelivery;
    }

    public void setEstimatedDelivery(String estimatedDelivery) {
        this.estimatedDelivery = estimatedDelivery;
    }

    public ShippingStatus getShippingStatus() {
        return shippingStatus;
    }

    public void setShippingStatus(ShippingStatus shippingStatus) {
        this.shippingStatus = shippingStatus;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }
}
