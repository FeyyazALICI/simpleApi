package com.simple_api.backend.components.business.interfaces;

import java.sql.SQLException;
import java.util.List;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;

public interface NativeServiceInterface {
    
    List<CatDTO> getAllCats() throws Exception; 
    CatNative getCatById( Long id ) throws Exception;

    boolean insertCat( CatNative catNative ) throws Exception;
    boolean updateCat( CatNative catNative ) throws Exception;
    boolean deleteCat(Long id) throws SQLException;

}
