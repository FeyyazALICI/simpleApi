package com.simple_api.backend.components.business.service.nativeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.business.dtoMapper.nativeMapper.CatNativeMapper2;
import com.simple_api.backend.components.business.interfaces.NativeServiceInterface;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;
import com.simple_api.backend.components.dao.repository.nativeRepository.CatNativeRepository;

@Service
public class NativeService implements NativeServiceInterface{
    

    private final CatNativeRepository catNativeRepository;

    @Autowired
    public NativeService(
        CatNativeRepository catNativeRepository
    ){
        this.catNativeRepository = catNativeRepository;
    }

    @Override
    public List<CatDTO> getAllCats() throws Exception{
        try{
            CatNativeMapper2 catNativeMapper2 = new CatNativeMapper2();

            List<CatNative> dataStage0 = this.catNativeRepository.getAllCats();
            List<CatDTO> data = new ArrayList<>();
            for(CatNative catNative: dataStage0){
                CatDTO catDTO = catNativeMapper2.catNativeToCatDTOMapper(catNative);
                data.add(catDTO);
            }
            return data;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean insertCat( CatNative catNative ) throws Exception{
        try{
            return this.catNativeRepository.insertCat(catNative);
        }catch( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean updateCat( CatNative catNative ) throws Exception{
        try{
            return this.catNativeRepository.updateCat(catNative);
        }catch( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public boolean deleteCat( Long id ) throws SQLException{
        try{
            return this.catNativeRepository.deleteCat(id);
        }catch( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    public CatNative getCatById( Long id ) throws Exception{
        try{
            return this.catNativeRepository.getCatById(id);
        }catch( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }
}
