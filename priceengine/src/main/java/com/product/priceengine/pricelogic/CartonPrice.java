package com.product.priceengine.pricelogic;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class CartonPrice implements PriceCalculator {
    private int cartons;
    private double pricePerCarton;

    @Override
    public BigDecimal calculatePrice() {
        return BigDecimal.valueOf(cartons).multiply(BigDecimal.valueOf(pricePerCarton)).setScale(2, BigDecimal.ROUND_UP);
    }
}
