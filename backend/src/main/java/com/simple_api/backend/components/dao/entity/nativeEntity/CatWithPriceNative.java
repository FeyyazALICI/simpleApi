package com.simple_api.backend.components.dao.entity.nativeEntity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CatWithPriceNative {

    @Column(name = "id")
    private Long id;

    @Column(name = "cat_name")
    private String name;

    @Column(name = "weight")
    private BigDecimal weight;

    @Column(name = "price")
    private BigDecimal price;
    
    public CatWithPriceNative(
        Long id,
        String name,
        BigDecimal weight,
        BigDecimal price
    ){
        this.id         = id;
        this.name       = name;
        this.weight     = weight;
        this.price      = price;
    }
}
