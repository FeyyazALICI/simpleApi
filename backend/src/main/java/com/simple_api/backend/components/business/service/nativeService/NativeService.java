package com.simple_api.backend.components.business.service.nativeService;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.business.dto.CatWithPriceDTO;
import com.simple_api.backend.components.business.dtoMapper.nativeMapper.CatNativeMapper2;
import com.simple_api.backend.components.business.dtoMapper.nativeMapper.CatWithPriceNativeMapper2;
import com.simple_api.backend.components.business.interfaces.NativeServiceInterface;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatWithPriceNative;
import com.simple_api.backend.components.dao.repository.nativeRepository.CatNativeRepository;
import com.simple_api.backend.components.dao.repository.nativeRepository.CatWithPriceRepoNative;

@Service
public class NativeService implements NativeServiceInterface{
    

    private final CatNativeRepository catNativeRepository;
    private final CatWithPriceRepoNative catWithPriceRepoNative;

    public NativeService(
        CatNativeRepository catNativeRepository,
        CatWithPriceRepoNative catWithPriceRepoNative
    ){
        this.catNativeRepository        = catNativeRepository;
        this.catWithPriceRepoNative     = catWithPriceRepoNative;
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

    // getting view
    @Override
    public List<CatWithPriceDTO> getAllCatWithPrice() throws Exception{
        try{
            CatWithPriceNativeMapper2 catWithPriceNativeMapper2 = new CatWithPriceNativeMapper2();

            List<CatWithPriceNative> dataStage0 = this.catWithPriceRepoNative.getAllCatWithPrice();
            List<CatWithPriceDTO> data = new ArrayList<>();
            for(CatWithPriceNative catWithPriceNative: dataStage0){
                CatWithPriceDTO catWithPriceDTO = catWithPriceNativeMapper2.nativeToDTO(catWithPriceNative);
                data.add(catWithPriceDTO);
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
