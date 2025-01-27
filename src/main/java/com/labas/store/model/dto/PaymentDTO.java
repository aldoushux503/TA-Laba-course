package com.labas.store.model.dto;

/**
 * DTO for Payment.
 */
public class PaymentDTO {
    private Long paymentId;
    private String createdAt;
    private String updatedAt;
    private Long paymentMethodId;
    private Long userId;
    private Long orderId;

    public PaymentDTO() {
    }

    public PaymentDTO(Long paymentId, String createdAt, String updatedAt, Long paymentMethodId, Long userId, Long orderId) {
        this.paymentId = paymentId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
        this.paymentMethodId = paymentMethodId;
        this.userId = userId;
        this.orderId = orderId;
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

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
