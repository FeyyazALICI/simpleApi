package com.simple_api.backend.components.business.dtoMapper.nativeMapper;

import java.math.BigDecimal;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.RowMapper;

import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;

public class CatNativeMapper implements RowMapper<CatNative>{
    
    @Autowired
    public CatNative mapRow(ResultSet rs, int rowNumber) throws SQLException{
        Long id = rs.getLong("id");
        String name = rs.getString("name");
        BigDecimal weight = rs.getBigDecimal("weight");

        CatNative catNative = new CatNative();
        catNative.setId(id);
        catNative.setName(name);
        catNative.setWeight(weight);
        return catNative;
    }
}


