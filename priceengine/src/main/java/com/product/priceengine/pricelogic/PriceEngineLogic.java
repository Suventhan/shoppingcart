package com.product.priceengine.pricelogic;

import com.product.priceengine.constant.UnitType;
import com.product.priceengine.dto.Item;
import com.product.priceengine.dto.ShoppingCart;
import com.product.priceengine.model.Product;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Component
@AllArgsConstructor
public class PriceEngineLogic {

    private final BeanFactory beanFactory;

    /***
     * Calculate the price for the shopping cart
     * @param shoppingCart
     * @param products
     */
    public void calculateShoppingCartPrice(ShoppingCart shoppingCart, List<Product> products) {
        Map<Integer, Product> productById = products.stream().collect(Collectors.toMap(Product::getId, product -> product));

        BigDecimal price = BigDecimal.ZERO;
        for (Item item : shoppingCart.getSelectedItems()) {
            if (productById.containsKey(item.getId())) {
                price = price.add(calculateItemPrice(item, productById.get(item.getId())));
            }
        }

        shoppingCart.setPrice(price.doubleValue());
    }

    /***
     * Calculate single item price
     * @param item
     * @param product
     * @return price
     */
    public BigDecimal calculateItemPrice(Item item, Product product) {
        BigDecimal bigDecimal;
        if (UnitType.CARTON.equals(item.getUnitType())) {
            bigDecimal = calculateCartonPrice(item, product);
        } else {
            bigDecimal = calculateCombinePrice(item, product);
        }

        item.setPrice(bigDecimal.doubleValue());
        return bigDecimal;
    }

    private BigDecimal calculateCombinePrice(Item item, Product product) {
        int units = item.getUnits()%product.getUnitsPerCarton();
        int cartons = item.getUnits()/product.getUnitsPerCarton();
        PriceCalculator itemPrice = beanFactory.getBean(UnitPrice.class, units, product.getPricePerCarton(), product.getUnitsPerCarton());
        PriceCalculator cartonPrice = beanFactory.getBean(CartonPrice.class, cartons, product.getPricePerCarton());

        PriceCalculator itemPriceCalculator = beanFactory.getBean(ItemPriceCalculator.class, itemPrice, cartonPrice);
        return checkAndApplyDiscount(itemPriceCalculator, product.getPricePerCarton(), cartons);
    }

    private BigDecimal calculateCartonPrice(Item item, Product product) {
        PriceCalculator cartonPrice = beanFactory.getBean(CartonPrice.class, item.getUnits(), product.getPricePerCarton());
        return checkAndApplyDiscount(cartonPrice, product.getPricePerCarton(), item.getUnits());
    }

    private BigDecimal checkAndApplyDiscount(PriceCalculator priceCalculator, double pricePerCarton, int cartons) {
        if (cartons >= 3) {
            return applyDiscount(priceCalculator, pricePerCarton);
        } else {
            return priceCalculator.calculatePrice();
        }
    }

    private BigDecimal applyDiscount(PriceCalculator priceCalculator, double pricePerCarton) {
        return beanFactory.getBean(DiscountDecorator.class, priceCalculator, pricePerCarton).calculatePrice();
    }
}
