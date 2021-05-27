package com.product.priceengine.dto;

import com.product.priceengine.constant.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    private int id;
    private String name;
    private int units;
    private double price;
    private UnitType unitType;
}
