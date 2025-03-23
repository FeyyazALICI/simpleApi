package com.simple_api.backend.components.business.service.jpaService;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.business.dto.CatWithPriceDTO;
import com.simple_api.backend.components.business.dtoMapper.jpaMapper.CatMapper;
import com.simple_api.backend.components.business.dtoMapper.jpaMapper.CatWithPriceMapper;
import com.simple_api.backend.components.business.interfaces.JpaServiceInterface;
import com.simple_api.backend.components.dao.entity.jpaEntity.Cat;
import com.simple_api.backend.components.dao.entity.jpaEntity.CatWithPrice;
import com.simple_api.backend.components.dao.repository.jpaRepository.CatRepoJpa;
import com.simple_api.backend.components.dao.repository.jpaRepository.CatWithPriceRepoJpa;

import jakarta.transaction.Transactional;

@Service
public class MainServiceJpa implements JpaServiceInterface{
    
    private final CatRepoJpa catRepoJpa;
    private final CatWithPriceRepoJpa catWithPriceRepoJpa;

    public MainServiceJpa(
        CatRepoJpa catRepoJpa,
        CatWithPriceRepoJpa catWithPriceRepoJpa
    ){
        this.catRepoJpa     = catRepoJpa;
        this.catWithPriceRepoJpa   = catWithPriceRepoJpa;
    }

    @Override
    public List<CatDTO> getAllCats(){
        try{
            CatMapper catMapper = new CatMapper();
            List<Cat> data = this.catRepoJpa.findAll();
            List<CatDTO> dataDTO = new ArrayList<>();
            for(Cat cat: data){
                CatDTO catDTO = new CatDTO();
                catDTO = catMapper.catToCatDTOMapper(cat);
                dataDTO.add(catDTO);
            }
            return dataDTO;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    // gettting view
    @Override
    public List<CatWithPriceDTO> getAllCatWithPrice(){
        try{
            CatWithPriceMapper catWithPriceMapper = new CatWithPriceMapper();
            List<CatWithPrice> data = this.catWithPriceRepoJpa.findAll();
            List<CatWithPriceDTO> dataDTO = new ArrayList<>();
            for(CatWithPrice catWithPrice: data){
                CatWithPriceDTO catWithPriceDTO = new CatWithPriceDTO();
                catWithPriceDTO = catWithPriceMapper.entityToDTO(catWithPrice);
                dataDTO.add(catWithPriceDTO);
            }
            return dataDTO;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    

    @Override
    public Cat getCatById( Long id ){
        try{
            Optional<Cat> data = this.catRepoJpa.findById(id);
            if(data.isPresent())
                return data.get();
            else
                return null;
        }catch(Exception e){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean insertCat( Cat cat ){
        try{
            if(cat.getName()!=null || !cat.getName().trim().equals("")){
                if( !ifCatExistsByName(cat.getName()) ){
                    cat.setId(null);
                    this.catRepoJpa.saveAndFlush(cat);
                    return true;
                }
                else{
                    System.out.println("Cat name is in use!");
                    return false;
                }
            }
            return false;
        }catch( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean updateCat( Cat cat ){
        try{
            if( ifCatExists( cat.getId() ) ){
                if( cat.getWeight()!=null && (cat.getWeight().compareTo(BigDecimal.ZERO)>0) ){
                    this.catRepoJpa.saveAndFlush(cat);
                    return true;
                }else{
                    System.out.println("Weight can not be empty nor can be equals and less than zero !");
                    return false;
                }
            }
            System.out.println("Cat does not exist!");
            return false;
        }catch( Exception e ){
            e.printStackTrace();
            throw e;
        }
    }

    @Override
    @Transactional
    public boolean deleteCat( Long id ){
        try{
            if( ifCatExists( id ) ){
                this.catRepoJpa.deleteById( id );
                return true;
            }
            System.out.println("Cat does not exist!");
            return false;
        }catch( Exception e ){
            System.out.println("Cat does not exist!");
            return false;
        }
    }

    @Override
    public boolean ifCatExistsByName( String name ){
        List<Cat> list = this.catRepoJpa.findByName(name);
        if(list!=null && list.size()>0)
            return true;
        return false;
    }

    @Override
    public boolean ifCatExists( Long id ){
        Optional<Cat> list = this.catRepoJpa.findById(id);
        if(   list.isPresent()   )
            return true;
        return false;
    }

    

}
