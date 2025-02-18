package com.labas.store.service.strategy;


public class FixedDiscountStrategy implements IDiscountStrategy {
    private final float discountAmount;

    public FixedDiscountStrategy(float discountAmount) {
        this.discountAmount = discountAmount;
    }

    @Override
    public float applyDiscount(float total) {
        return total - discountAmount;
    }
}
