package com.labas.store.model.dto;

/**
 * DTO for PaymentMethod.
 */
public class PaymentMethodDTO {
    private Long paymentMethodId;
    private String name;

    public PaymentMethodDTO() {
    }

    public PaymentMethodDTO(Long paymentMethodId, String name) {
        this.paymentMethodId = paymentMethodId;
        this.name = name;
    }

    public Long getPaymentMethodId() {
        return paymentMethodId;
    }

    public void setPaymentMethodId(Long paymentMethodId) {
        this.paymentMethodId = paymentMethodId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
