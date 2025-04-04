package com.simple_api.backend.components.dao.entity.nativeEntity;

import java.math.BigDecimal;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatNative {

    @Column(name = "id")
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "weight")
    private BigDecimal weight;
    
    public CatNative(
        Long id,
        String name,
        BigDecimal weight
    ){
        this.id     = id;
        this.name   = name;
        this.weight = weight;
    }
}
