package com.product.priceengine.controller;

import com.product.priceengine.dto.Item;
import com.product.priceengine.model.Product;
import com.product.priceengine.dto.ShoppingCart;
import com.product.priceengine.service.PricingService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@CrossOrigin(origins = "http://localhost:8081")
@RestController
@AllArgsConstructor
public class PricingController {

    private final PricingService pricingService;

    @GetMapping("/itemPrices")
    public List<Item> allItemPrices() {
        return pricingService.getAllItemPrices();
    }

    @GetMapping("/products")
    public List<Product> allProducts() {
        return pricingService.getAllProducts();
    }

    @PostMapping("/calculatePrice")
    public ShoppingCart calculatePrice(@Valid @RequestBody ShoppingCart shoppingCart) {
        return pricingService.calculatePrice(shoppingCart);
    }
}
