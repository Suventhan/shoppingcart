package com.product.priceengine.service;

import com.product.priceengine.dto.Item;
import com.product.priceengine.model.Product;
import com.product.priceengine.dto.ShoppingCart;

import java.util.List;

public interface PricingService {

    public List<Item> getAllItemPrices();

    public List<Product> getAllProducts();

    public ShoppingCart calculatePrice(ShoppingCart shoppingCart);
}
