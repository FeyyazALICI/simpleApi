package com.simple_api.backend.components.api.controller.jpaController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_api.backend.common.errorMessage.ErrorMessageDerived;
import com.simple_api.backend.common.responseEntityReturns.HttpHeaderCreator;
import com.simple_api.backend.components.api.apiInterfaces.ControllerInterface;
import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.business.dto.SingleAttrDTO;
import com.simple_api.backend.components.business.dtoMapper.jpaMapper.CatMapper;
import com.simple_api.backend.components.business.service.excelService.ExcelService;
import com.simple_api.backend.components.business.service.jpaService.MainServiceJpa;
import com.simple_api.backend.components.dao.entity.jpaEntity.Cat;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;


@RequestMapping("/jpa")
@RestController
public class JpaController implements ControllerInterface{
    
    private final MainServiceJpa serviceJpa;
    private final HttpHeaderCreator headerCreator;
    private final ExcelService excelService;

    @Autowired
    public JpaController(
        MainServiceJpa serviceJpa,
        HttpHeaderCreator headerCreator,
        ExcelService excelService
    ){
        this.serviceJpa     = serviceJpa;
        this.headerCreator  = headerCreator;
        this.excelService   = excelService;
    }

    @Override
    @GetMapping()
    public ResponseEntity getAllCats(HttpServletRequest request){
        String requestType = "GET";
        try{
            List<CatDTO> data = serviceJpa.getAllCats();
            if(   data!=null   ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>(data, responseHeader, HttpStatus.OK);
            }else{
                HttpHeaders responseHeader = this.headerCreator.notFoundResponseHeader(request, requestType);
                return new ResponseEntity<>(null, responseHeader, HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping("/id")
    public ResponseEntity getCatById(HttpServletRequest request, @Valid @RequestBody SingleAttrDTO singleAttrDTO   ){
        CatMapper catMapper = new CatMapper();
        String requestType = "GET";
        try{
            Long id = Long.valueOf(singleAttrDTO.getAttr0());
            Cat dataRaw = serviceJpa.getCatById(   id   );
            CatDTO data = catMapper.catToCatDTOMapper(dataRaw);
            if(   data!=null   ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>(data, responseHeader, HttpStatus.OK);
            }else{
                HttpHeaders responseHeader = this.headerCreator.notFoundResponseHeader(request, requestType);
                return new ResponseEntity<>(null, responseHeader, HttpStatus.NOT_FOUND);
            }
        }catch(Exception e){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PostMapping()
    public ResponseEntity insertCat(   HttpServletRequest request, @Valid @RequestBody CatDTO catDTO   ){
        CatMapper catMapper = new CatMapper();
        Cat cat = catMapper.catDTOtoCatMapper( catDTO );
        ErrorMessageDerived emd = new ErrorMessageDerived();
        String requestType = "POST";
        try{
            if( serviceJpa.insertCat(cat) ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>(cat, responseHeader, HttpStatus.OK);
            }
            HttpHeaders responseHeader = this.headerCreator.conflictResponseHeader(request, requestType);
            return new ResponseEntity<>( emd.conflictError(), responseHeader, HttpStatus.CONFLICT);
        }catch( Exception e ){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @PutMapping()
    public ResponseEntity updateCat(   HttpServletRequest request, @Valid @RequestBody CatDTO catDTO   ){
        CatMapper catMapper = new CatMapper();
        Cat cat = catMapper.catDTOtoCatMapper( catDTO );
        ErrorMessageDerived emd = new ErrorMessageDerived();
        String requestType = "PUT";
        try{
            if( serviceJpa.updateCat(cat) ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>(cat, responseHeader, HttpStatus.OK);
            }
            HttpHeaders responseHeader = this.headerCreator.notFoundResponseHeader(request, requestType);
            return new ResponseEntity<>( emd.notFoundError(cat), responseHeader, HttpStatus.NOT_FOUND);
        }catch( Exception e ){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity<Map<String, String>> deleteCat(   HttpServletRequest request, @Valid @RequestBody SingleAttrDTO dataReceived   ){
        Long id = Long.valueOf( dataReceived.getAttr0() );
        String requestType = "DELETE";
        try{
            if( serviceJpa.deleteCat(id) ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                Map<String, String> responseBody = new HashMap<>();
                responseBody.put("message", "Delete Operation is successful!");
                return new ResponseEntity<>(responseBody, responseHeader, HttpStatus.OK);
            }
            ErrorMessageDerived emd = new ErrorMessageDerived();
            HttpHeaders responseHeader = this.headerCreator.conflictResponseHeader(request, requestType);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", emd.notFoundErrorById(id));
            return new ResponseEntity<>( responseBody, responseHeader, HttpStatus.CONFLICT);
        }catch( Exception e ){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            Map<String, String> responseBody = new HashMap<>();
            responseBody.put("error", "Internal Server Error");
            return new ResponseEntity<>(responseBody, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }


    @Override
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportAllCatsToExcel(HttpServletRequest request) throws Exception {
        String requestType = "GET";
        try{
            List<CatDTO> data = serviceJpa.getAllCats();
            return this.excelService.exportAllCatsToExcel(data);
        }catch(Exception e){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    


}
