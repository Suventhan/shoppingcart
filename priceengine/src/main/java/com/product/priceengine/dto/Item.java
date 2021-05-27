package com.product.priceengine.dto;

import com.product.priceengine.constant.UnitType;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;

@Getter
@Setter
@AllArgsConstructor
public class Item {

    @NotNull
    private Integer id;
    private String name;
    @NotNull
    private Integer units;
    private double price;
    @NotNull
    private UnitType unitType;
}
