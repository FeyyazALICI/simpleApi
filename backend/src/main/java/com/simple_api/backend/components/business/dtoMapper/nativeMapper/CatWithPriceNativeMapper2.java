package com.simple_api.backend.components.business.dtoMapper.nativeMapper;

import java.math.BigDecimal;

import com.simple_api.backend.components.business.dto.CatWithPriceDTO;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatWithPriceNative;

public class CatWithPriceNativeMapper2{
    
    public CatWithPriceDTO nativeToDTO( CatWithPriceNative catWithPriceNative ){
        
        String id       = catWithPriceNative.getId().toString();
        String name     = catWithPriceNative.getName();
        String weight   = catWithPriceNative.getWeight().toString();
        String price    = catWithPriceNative.getPrice().toString();

        CatWithPriceDTO catWithPriceDTO = new CatWithPriceDTO();
        catWithPriceDTO.setId(id);
        catWithPriceDTO.setName(name);
        catWithPriceDTO.setWeight(weight);
        catWithPriceDTO.setPrice(price);

        return catWithPriceDTO;
    }


    public CatWithPriceNative dTOToNative( CatWithPriceDTO catWithPriceDTO ){
        Long id             = null; 
        String name         = null;
        BigDecimal weight   =  null;  
        BigDecimal price    =  null;  

        if(   catWithPriceDTO.getId()!=null   )
            id = Long.parseLong(   catWithPriceDTO.getId()   );  
        if(   catWithPriceDTO.getName()!=null   )
            name = catWithPriceDTO.getName();
        if(   catWithPriceDTO.getWeight()!=null   )
            weight =  new BigDecimal(   catWithPriceDTO.getWeight()   );  
        if(   catWithPriceDTO.getPrice()!=null   )
            price =  new BigDecimal(   catWithPriceDTO.getPrice()   );  

        CatWithPriceNative catWithPriceNative = new CatWithPriceNative();
        catWithPriceNative.setId(id);
        catWithPriceNative.setName(name);
        catWithPriceNative.setWeight(weight);
        catWithPriceNative.setPrice(price);

        return catWithPriceNative;
    }
}


