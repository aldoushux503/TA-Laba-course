package com.labas.store.service.strategy;

// Strategy for Order Discounts
public interface IDiscountStrategy {
    float applyDiscount(float total);
}
