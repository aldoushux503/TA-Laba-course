package com.labas.store.service.strategy;

public class PercentageDiscountStrategy implements IDiscountStrategy {
    private final float percentage;

    public PercentageDiscountStrategy(float percentage) {
        this.percentage = percentage;
    }

    @Override
    public float applyDiscount(float total) {
        return total - (total * (percentage / 100));
    }
}