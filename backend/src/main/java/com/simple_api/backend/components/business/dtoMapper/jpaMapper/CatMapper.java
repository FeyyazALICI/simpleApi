package com.simple_api.backend.components.business.dtoMapper.jpaMapper;

import java.math.BigDecimal;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.dao.entity.jpaEntity.Cat;

public class CatMapper {

    public Cat catDTOtoCatMapper( CatDTO catDTO ){
        
        Long id = null;
        if( catDTO.getId()==null || ( catDTO.getId().trim().equals("") ) ){
            id = null;
        }else{
            id = Long.parseLong(catDTO.getId());
        }

        String name = null;
        if( catDTO.getName()==null || ( catDTO.getName().trim().equals("") )){
            name = null;
        }else{
            name = catDTO.getName();
        }

        BigDecimal weight = null;
        if( catDTO.getWeight()==null || ( catDTO.getWeight().trim().equals("") )){
            weight = null;
        }else{
            weight = new BigDecimal(catDTO.getWeight()) ;
        }

        Cat cat = new Cat();
        cat.setId(id);
        cat.setName(name);
        cat.setWeight(weight);

        return cat;
    }

    public CatDTO catToCatDTOMapper( Cat cat ){
        
        String id = cat.getId().toString();
        String name = cat.getName();
        String weight = cat.getWeight().toString();

        CatDTO catDTO = new CatDTO();
        catDTO.setId(id);
        catDTO.setName(name);
        catDTO.setWeight(weight);

        return catDTO;
    }
    
}