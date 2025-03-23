package com.simple_api.backend.components.dao.repository.nativeRepository;

import java.util.List;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import com.simple_api.backend.components.business.dtoMapper.nativeMapper.CatWithPriceNativeMapper;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatWithPriceNative;

@Repository
public class CatWithPriceRepoNative {
    
    private final JdbcTemplate jdbcTemplate;

    public CatWithPriceRepoNative(
        JdbcTemplate jdbcTemplate
    ){
        this.jdbcTemplate = jdbcTemplate;
    } 
    
    public List<CatWithPriceNative> getAllCatWithPrice(){
        StringBuilder stb = new StringBuilder();
        stb.append("SELECT                  ");
        stb.append("    v.cat_id,           ");
        stb.append("    v.cat_name,         ");
        stb.append("    v.weight,           ");
        stb.append("    v.price             ");
        stb.append("FROM                    ");
        stb.append("    cat_with_price v    ");
        
        List<CatWithPriceNative> resultList = jdbcTemplate.query(stb.toString(), new CatWithPriceNativeMapper());

        return resultList;
    }
}
