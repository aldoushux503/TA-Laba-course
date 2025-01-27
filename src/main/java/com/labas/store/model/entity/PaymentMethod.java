package com.labas.store.model.entity;

/**
 * Represents a payment method (e.g., Credit Card, PayPal).
 */
public class PaymentMethod {
    private Long paymentMethodId;
    private String name;

    public PaymentMethod() {}

    public PaymentMethod(Long paymentMethodId, String name) {
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
