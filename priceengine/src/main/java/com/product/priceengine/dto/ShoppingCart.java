package com.product.priceengine.dto;

import lombok.Getter;
import lombok.Setter;

import javax.validation.Valid;
import javax.validation.constraints.NotEmpty;
import java.util.List;

@Getter
@Setter
public class ShoppingCart {

    @NotEmpty
    private List<@Valid Item> selectedItems;
    private double price;
}
