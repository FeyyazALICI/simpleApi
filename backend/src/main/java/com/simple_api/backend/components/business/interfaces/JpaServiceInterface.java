package com.simple_api.backend.components.business.interfaces;

import java.util.List;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.dao.entity.jpaEntity.Cat;

public interface JpaServiceInterface {
    
    List<CatDTO> getAllCats();
    Cat getCatById( Long id );

    boolean insertCat( Cat cat );
    boolean updateCat( Cat cat );
    boolean deleteCat( Long id );

    boolean ifCatExists( Long id );
    boolean ifCatExistsByName( String name );
}
