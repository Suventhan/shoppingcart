package com.product.priceengine.pricelogic;

import java.math.BigDecimal;

public class DiscountDecorator extends PriceDecorator {

    public static final BigDecimal ONE_HUNDRED = new BigDecimal(100);
    public static final BigDecimal DISCOUNT = new BigDecimal(10);

    private final double pricePerCarton;

    public DiscountDecorator(PriceCalculator priceCalculator, double pricePerCarton) {
        super(priceCalculator);
        this.pricePerCarton = pricePerCarton;
    }

    @Override
    public BigDecimal calculatePrice() {
        return super.calculatePrice().subtract(BigDecimal.valueOf(pricePerCarton).multiply(DISCOUNT).divide(ONE_HUNDRED, BigDecimal.ROUND_UP));
    }
}
