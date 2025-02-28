package com.simple_api.backend.components.dao.repository.jpaRepository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.simple_api.backend.components.dao.entity.jpaEntity.Cat;
import java.util.List;


public interface CatRepoJpa extends JpaRepository<Cat, Long>{
    

    List<Cat> findByName(String name);
}
