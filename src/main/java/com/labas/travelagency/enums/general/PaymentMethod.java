package com.labas.travelagency.enums.general;

public enum PaymentMethod {
    CREDIT_CARD("Credit Card", "Payment through bank card"),
    PAYPAL("PayPal", "Online payment platform"),
    BANK_TRANSFER("Bank Transfer", "Direct transfer to bank account");

    private final String name;
    private final String description;

    PaymentMethod(String name, String description) {
        this.name = name;
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

}