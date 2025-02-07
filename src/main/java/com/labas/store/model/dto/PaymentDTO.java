package com.labas.store.model.dto;

import java.sql.Time;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * DTO for Payment.
 */
public class PaymentDTO {
    private Long paymentId;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;
    private Long paymentMethodId;
    private Long userId;
    private Long orderId;

    public PaymentDTO() {
    }

    public PaymentDTO(Long paymentId, LocalDateTime createdAt, LocalDateTime updatedAt, Long paymentMethodId, Long userId, Long orderId) {
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

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public LocalDateTime getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(LocalDateTime updatedAt) {
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
