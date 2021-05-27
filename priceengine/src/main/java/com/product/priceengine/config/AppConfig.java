package com.product.priceengine.config;

import com.product.priceengine.pricelogic.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Scope;

@Configuration
public class AppConfig {

    @Bean
    @Scope(value = "prototype")
    public UnitPrice unitPrice(int units, double pricePerCarton, int unitsPerCarton) {
        return new UnitPrice(units, pricePerCarton, unitsPerCarton);
    }

    @Bean
    @Scope(value = "prototype")
    public CartonPrice cartonPrice(int cartons, double pricePerCarton) {
        return new CartonPrice(cartons, pricePerCarton);
    }

    @Bean
    @Scope(value = "prototype")
    public ItemPriceCalculator itemPriceCalculator(UnitPrice unitPrice, CartonPrice cartonPrice) {
        return new ItemPriceCalculator(unitPrice, cartonPrice);
    }

    @Bean
    @Scope(value = "prototype")
    public DiscountDecorator discountDecorator(PriceCalculator priceCalculator, double pricePerCarton) {
        return new DiscountDecorator(priceCalculator, pricePerCarton);
    }
}
