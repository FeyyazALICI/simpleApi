package com.simple_api.backend.components.business.dtoMapper.nativeMapper;

import java.math.BigDecimal;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;

public class CatNativeMapper2 {
    

    public CatDTO catNativeToCatDTOMapper( CatNative catNative ){
        
        String id = catNative.getId().toString();
        String name = catNative.getName();
        String weight = catNative.getWeight().toString();

        CatDTO catDTO = new CatDTO();
        catDTO.setId(id);
        catDTO.setName(name);
        catDTO.setWeight(weight);

        return catDTO;
    }


    public CatNative CatDTOToCatNativeMapper( CatDTO catDTO ){
        Long id = null; 
        String name = null;
        BigDecimal weight =  null;  

        if(   catDTO.getId()!=null   )
            id = Long.parseLong(   catDTO.getId()   );  
        if(   catDTO.getName()!=null   )
            name = catDTO.getName();
        if(   catDTO.getWeight()!=null   )
            weight =  new BigDecimal(   catDTO.getWeight()   );  

        CatNative catNative = new CatNative();
        catNative.setId(id);
        catNative.setName(name);
        catNative.setWeight(weight);

        return catNative;
    }

    
}
