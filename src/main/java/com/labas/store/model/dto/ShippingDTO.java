package com.labas.store.model.dto;

/**
 * DTO for Shipping.
 */
public class ShippingDTO {
    private Long shippingId;
    private String shippingCarrier;
    private String trackingNumber;
    private String shippedAt;
    private String estimatedDelivery;
    private Long shippingStatusId;
    private Long orderId;

    public ShippingDTO() {
    }

    public ShippingDTO(Long shippingId, String shippingCarrier, String trackingNumber, String shippedAt, String estimatedDelivery, Long shippingStatusId, Long orderId) {
        this.shippingId = shippingId;
        this.shippingCarrier = shippingCarrier;
        this.trackingNumber = trackingNumber;
        this.shippedAt = shippedAt;
        this.estimatedDelivery = estimatedDelivery;
        this.shippingStatusId = shippingStatusId;
        this.orderId = orderId;
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

    public Long getShippingStatusId() {
        return shippingStatusId;
    }

    public void setShippingStatusId(Long shippingStatusId) {
        this.shippingStatusId = shippingStatusId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
