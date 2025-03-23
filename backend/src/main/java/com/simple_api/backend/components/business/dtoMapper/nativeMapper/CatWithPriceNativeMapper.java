package com.simple_api.backend.components.business.dtoMapper.nativeMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.simple_api.backend.components.dao.entity.nativeEntity.CatWithPriceNative;

public class CatWithPriceNativeMapper implements RowMapper<CatWithPriceNative>{
    
    @Autowired
    public CatWithPriceNative mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Long id             = rs.getLong("cat_id");
        String name         = rs.getString("cat_name");
        BigDecimal weight   = rs.getBigDecimal("weight");
        BigDecimal price    = rs.getBigDecimal("price");

        CatWithPriceNative catWithPriceNative = new CatWithPriceNative();
        catWithPriceNative.setId(id);
        catWithPriceNative.setName(name);
        catWithPriceNative.setWeight(weight);
        catWithPriceNative.setPrice(price);
        return catWithPriceNative;
    }


}


