package com.simple_api.backend.components.business.dtoMapper.jpaMapper;

import java.math.BigDecimal;

import com.simple_api.backend.components.business.dto.CatWithPriceDTO;
import com.simple_api.backend.components.dao.entity.jpaEntity.CatWithPrice;

public class CatWithPriceMapper {

    public CatWithPrice dTOtoEntity( CatWithPriceDTO catWithPriceDTO ){
        
        Long id = null;
        if( catWithPriceDTO.getId()==null || ( catWithPriceDTO.getId().trim().equals("") ) ){
            id = null;
        }else{
            id = Long.parseLong(catWithPriceDTO.getId());
        }

        String name = null;
        if( catWithPriceDTO.getName()==null || ( catWithPriceDTO.getName().trim().equals("") )){
            name = null;
        }else{
            name = catWithPriceDTO.getName();
        }

        BigDecimal weight = null;
        if( catWithPriceDTO.getWeight()==null || ( catWithPriceDTO.getWeight().trim().equals("") )){
            weight = null;
        }else{
            weight = new BigDecimal(catWithPriceDTO.getWeight()) ;
        }

        BigDecimal price = null;
        if( catWithPriceDTO.getPrice()==null || ( catWithPriceDTO.getPrice().trim().equals("") )){
            price = null;
        }else{
            price = new BigDecimal(catWithPriceDTO.getPrice()) ;
        }

        CatWithPrice catWithPrice = new CatWithPrice();
        catWithPrice.setId(id);
        catWithPrice.setName(name);
        catWithPrice.setWeight(weight);
        catWithPrice.setPrice(price);

        return catWithPrice;
    }

    public CatWithPriceDTO entityToDTO( CatWithPrice catWithPrice ){
        
        String id       = catWithPrice.getId().toString();
        String name     = catWithPrice.getName();
        String weight   = catWithPrice.getWeight().toString();
        String price    = catWithPrice.getPrice().toString();

        CatWithPriceDTO catWithPriceDTO = new CatWithPriceDTO();
        catWithPriceDTO.setId(id);
        catWithPriceDTO.setName(name);
        catWithPriceDTO.setWeight(weight);
        catWithPriceDTO.setPrice(price);

        return catWithPriceDTO;
    }

       
    
}