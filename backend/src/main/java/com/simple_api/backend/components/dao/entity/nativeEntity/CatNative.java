package com.simple_api.backend.components.dao.entity.nativeEntity;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.Column;
import lombok.Data;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Data
public class CatNative {

    @Column(name = "id")
    @JsonProperty("id")
    private Long id;

    @Column(name = "name")
    @JsonProperty("name")
    private String name;

    @Column(name = "weight")
    @JsonProperty("weight")
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
