package com.labas.travelagency.manager.strategy;

/**
 * Interface for defining tax strategies.
 */
public interface TaxStrategy {
    double applyTax(double price);
}
