package com.product.priceengine.service;

import com.product.priceengine.constant.UnitType;
import com.product.priceengine.dto.Item;
import com.product.priceengine.model.Product;
import com.product.priceengine.pricelogic.PriceEngine;
import com.product.priceengine.dto.ShoppingCart;
import com.product.priceengine.repository.ProductRepository;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class PricingServiceImpl implements PricingService {

    private static final int TOTAL_ITEMS = 50;

    private final ProductRepository productRepository;

    @Override
    public List<Item> getAllItemPrices() {
        List<Product> products = productRepository.findAll();

        return generateAndCalculatePrice(products);
    }

    @Override
    public List<Product> getAllProducts() {
        return productRepository.findAll();
    }

    @Override
    public ShoppingCart calculatePrice(ShoppingCart shoppingCart) {
        List<Product> products = productRepository.findAllById(shoppingCart.
                getSelectedItems().stream().map(Item::getId).collect(Collectors.toList()));
        PriceEngine.calculateShoppingCartPrice(shoppingCart, products);

        return shoppingCart;
    }

    private List<Item> generateAndCalculatePrice(List<Product> products) {
        List<Item> items = new ArrayList<>();
        products.forEach(product -> {
            for (int i = 1; i <= TOTAL_ITEMS; i++) {
                Item item = createItem(product, i);
                items.add(item);
            }
        });

        return items;
    }

    private Item createItem(Product product, int units) {
        Item item = new Item(product.getId(), product.getName(), units, 0, UnitType.UNIT);

        PriceEngine.calculateItemPrice(item, product);
        return item;
    }
}
