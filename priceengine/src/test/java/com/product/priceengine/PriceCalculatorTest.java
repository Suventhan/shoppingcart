package com.product.priceengine;

import com.product.priceengine.pricelogic.*;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

import java.math.BigDecimal;

public class PriceCalculatorTest {

    @Test
    public void testCartonCalculator() {
        PriceCalculator cartonPrice = new CartonPrice(2, 825);

        BigDecimal value = cartonPrice.calculatePrice();
        BigDecimal expected = new BigDecimal("1650.00").setScale(2, BigDecimal.ROUND_UP);
        assertThat(value).isEqualTo(expected);
    }

    @Test
    public void testUnitCalculator() {
        PriceCalculator itemPrice = new UnitPrice(4, 825, 5);

        BigDecimal value = itemPrice.calculatePrice();
        BigDecimal expected = new BigDecimal("858.00").setScale(2, BigDecimal.ROUND_UP);
        assertThat(value).isEqualTo(expected);
    }

    @Test
    public void testCompositeCalculator() {
        PriceCalculator itemPrice = new UnitPrice(4, 825, 5);
        PriceCalculator cartonPrice = new CartonPrice(2, 825);

        PriceCalculator itemPriceCalculator = new ItemPriceCalculator(itemPrice, cartonPrice);

        BigDecimal value = itemPriceCalculator.calculatePrice();
        BigDecimal expected = new BigDecimal("2508.00").setScale(2, BigDecimal.ROUND_UP);
        assertThat(value).isEqualTo(expected);
    }

    @Test
    public void testCompositeCalculatorWithDiscount() {
        PriceCalculator itemPrice = new UnitPrice(4, 825, 5);
        PriceCalculator cartonPrice = new CartonPrice(3, 825);

        PriceCalculator itemPriceCalculator = new ItemPriceCalculator(itemPrice, cartonPrice);

        PriceDecorator discountDecorator = new DiscountDecorator(itemPriceCalculator,825);

        BigDecimal value = discountDecorator.calculatePrice();
        BigDecimal expected = new BigDecimal("3250.50").setScale(2, BigDecimal.ROUND_UP);
        assertThat(value).isEqualTo(expected);
    }
}
