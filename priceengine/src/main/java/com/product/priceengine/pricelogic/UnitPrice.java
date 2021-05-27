package com.product.priceengine.pricelogic;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class UnitPrice implements PriceCalculator {
    private int units;
    private double pricePerCarton;
    private int unitsPerCarton;

    @Override
    public BigDecimal calculatePrice() {
        return BigDecimal.valueOf(units).multiply(BigDecimal.valueOf(pricePerCarton)
                .divide(BigDecimal.valueOf(unitsPerCarton), BigDecimal.ROUND_UP)).multiply(BigDecimal.valueOf(1.3));
    }
}
