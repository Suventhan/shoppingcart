package com.product.priceengine.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class ShoppingCart {

    private List<Item> selectedItems;
    private double price;
}
