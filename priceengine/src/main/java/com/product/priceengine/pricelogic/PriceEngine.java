package com.product.priceengine.pricelogic;

import com.product.priceengine.constant.UnitType;
import com.product.priceengine.dto.Item;
import com.product.priceengine.dto.ShoppingCart;
import com.product.priceengine.model.Product;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class PriceEngine {

    public static void calculateShoppingCartPrice(ShoppingCart shoppingCart, List<Product> products) {
        Map<Integer, Product> productById = products.stream().collect(Collectors.toMap(Product::getId, product -> product));

        BigDecimal price = BigDecimal.ZERO;
        for (Item item : shoppingCart.getSelectedItems()) {
            price = price.add(calculateItemPrice(item, productById.get(item.getId())));
        }

        shoppingCart.setPrice(price.doubleValue());
    }

    public static BigDecimal calculateItemPrice(Item item, Product product) {
        BigDecimal bigDecimal;
        if (UnitType.CARTON.equals(item.getUnitType())) {
            bigDecimal = calculateCartonPrice(item, product);
        } else {
            bigDecimal = calculateCombinePrice(item, product);
        }

        item.setPrice(bigDecimal.doubleValue());
        return bigDecimal;
    }

    private static BigDecimal calculateCombinePrice(Item item, Product product) {
        int units = item.getUnits()%product.getUnitsPerCarton();
        int cartons = item.getUnits()/product.getUnitsPerCarton();
        PriceCalculator itemPrice = new UnitPrice(units, product.getPricePerCarton(), product.getUnitsPerCarton());
        PriceCalculator cartonPrice = new CartonPrice(cartons, product.getPricePerCarton());

        PriceCalculator itemPriceCalculator = new ItemPriceCalculator(itemPrice, cartonPrice);
        return checkAndApplyDiscount(itemPriceCalculator, product.getPricePerCarton(), cartons);
    }

    private static BigDecimal calculateCartonPrice(Item item, Product product) {
        PriceCalculator cartonPrice = new CartonPrice(item.getUnits(), product.getPricePerCarton());
        return checkAndApplyDiscount(cartonPrice, product.getPricePerCarton(), item.getUnits());
    }

    private static BigDecimal checkAndApplyDiscount(PriceCalculator priceCalculator, double pricePerCarton, int cartons) {
        if (cartons >= 3) {
            return applyDiscount(priceCalculator, pricePerCarton);
        } else {
            return priceCalculator.calculatePrice();
        }
    }

    private static BigDecimal applyDiscount(PriceCalculator priceCalculator, double pricePerCarton) {
        return new DiscountDecorator(priceCalculator, pricePerCarton).calculatePrice();
    }
}
