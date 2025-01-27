package com.labas.store.model.entity;

/**
 * Represents a shipping status.
 */
public class ShippingStatus {
    private Long shippingStatusId;
    private String statusName;

    public ShippingStatus() {}

    public ShippingStatus(Long shippingStatusId, String statusName) {
        this.shippingStatusId = shippingStatusId;
        this.statusName = statusName;
    }

    public Long getShippingStatusId() {
        return shippingStatusId;
    }

    public void setShippingStatusId(Long shippingStatusId) {
        this.shippingStatusId = shippingStatusId;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }
}