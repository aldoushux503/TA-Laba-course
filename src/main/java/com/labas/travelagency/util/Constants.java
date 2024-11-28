package com.labas.travelagency.util;

import com.labas.travelagency.manager.strategy.DefaultTaxStrategy;
import com.labas.travelagency.manager.strategy.LuxuryTaxStrategy;

public final class Constants {
    public static final double DEFAULT_TOUR_TAX = 1.15;
    public static final double LUXURY_TOUR_TAX = 10.10;
    public static final DefaultTaxStrategy DEFAULT_TAX_STRATEGY = new DefaultTaxStrategy();
    public static final LuxuryTaxStrategy LUXURY_TAX_STRATEGY = new LuxuryTaxStrategy();

    private Constants() {

    }

    static {
        System.out.println("Constants class loaded with default tax: " + DEFAULT_TOUR_TAX);
    }
}
