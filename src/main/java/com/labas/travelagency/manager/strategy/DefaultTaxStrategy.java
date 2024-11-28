package com.labas.travelagency.manager.strategy;

import com.labas.travelagency.util.Constants;

/**
 * Default tax strategy.
 */
public class DefaultTaxStrategy implements TaxStrategy {
    @Override
    public final double applyTax(double price) {
        return price * Constants.DEFAULT_TOUR_TAX;
    }
}