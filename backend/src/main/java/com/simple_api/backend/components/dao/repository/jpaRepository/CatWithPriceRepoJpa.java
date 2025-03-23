package com.simple_api.backend.components.dao.repository.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple_api.backend.components.dao.entity.jpaEntity.CatWithPrice;

public interface CatWithPriceRepoJpa extends JpaRepository<CatWithPrice, Long>{
    
}
