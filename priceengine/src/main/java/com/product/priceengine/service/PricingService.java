package com.product.priceengine.service;

import com.product.priceengine.dto.Item;
import com.product.priceengine.model.Product;
import com.product.priceengine.dto.ShoppingCart;

import java.util.List;

public interface PricingService {

    /***
     * Get all product items with actual price
     * @return items
     */
    public List<Item> getAllItemPrices();

    /***
     * Get all products
     * @return products
     */
    public List<Product> getAllProducts();

    /***
     * calculate the price for shopping cart
     * @param shoppingCart
     * @return shoppingCart with price
     */
    public ShoppingCart calculatePrice(ShoppingCart shoppingCart);
}
