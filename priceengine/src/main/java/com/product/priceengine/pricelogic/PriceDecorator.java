package com.product.priceengine.pricelogic;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public abstract class PriceDecorator implements PriceCalculator {
    private PriceCalculator priceCalculator;

    @Override
    public BigDecimal calculatePrice() {
        return priceCalculator.calculatePrice();
    }
}
