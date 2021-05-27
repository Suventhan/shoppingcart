package com.product.priceengine.pricelogic;

import lombok.AllArgsConstructor;

import java.math.BigDecimal;

@AllArgsConstructor
public class ItemPriceCalculator implements PriceCalculator{
    private final PriceCalculator unitPrice;
    private final PriceCalculator cartonPrice;

    @Override
    public BigDecimal calculatePrice() {
        return unitPrice.calculatePrice().add(cartonPrice.calculatePrice());
    }
}
