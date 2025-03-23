package com.simple_api.backend.components.dao.entity.jpaEntity;

import java.math.BigDecimal;

import org.hibernate.annotations.Immutable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@NoArgsConstructor
@Immutable // Prevent updates -> because the view is read only
@Table(name = "cat_with_price") // Make sure to point to the correct view name
public class CatWithPrice {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "cat_id")  
    private Long id;

    @Column(name = "cat_name")  
    private String name;

    @Column(name = "weight")  
    private BigDecimal weight;

    @Column(name = "price")  
    private BigDecimal price;
    
    public CatWithPrice(
        Long id,
        String name,
        BigDecimal weight,
        BigDecimal price
    ){
        this.id = id;
        this.name = name;
        this.weight = weight;
        this.price = price;
    }
}