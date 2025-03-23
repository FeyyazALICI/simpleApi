package com.simple_api.backend.components.api.controller.nativeController;

import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.simple_api.backend.common.errorMessage.ErrorMessageDerived;
import com.simple_api.backend.common.responseEntityReturns.HttpHeaderCreator;
import com.simple_api.backend.components.api.apiInterfaces.ControllerInterface;
import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.business.dto.CatWithPriceDTO;
import com.simple_api.backend.components.business.dto.SingleAttrDTO;
import com.simple_api.backend.components.business.dtoMapper.nativeMapper.CatNativeMapper2;
import com.simple_api.backend.components.business.service.excelService.ExcelService;
import com.simple_api.backend.components.business.service.nativeService.NativeService;
import com.simple_api.backend.components.dao.entity.nativeEntity.CatNative;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;


@RestController
@RequestMapping("/native")
public class NativeController implements ControllerInterface{
    
    private final NativeService nativeService;
    private final HttpHeaderCreator headerCreator;
    private final ExcelService excelService;

    public NativeController(
        NativeService nativeService,
        HttpHeaderCreator headerCreator,
        ExcelService excelService
    ){
        this.nativeService = nativeService;
        this.headerCreator = headerCreator;
        this.excelService = excelService;
    }

    @Override
    @GetMapping()
    public ResponseEntity getAllCats(HttpServletRequest request){
        String requestType = "GET";
        try{
            List<CatDTO> data = nativeService.getAllCats();
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
    @GetMapping("/view")
    public ResponseEntity getAllCatWithPrice(HttpServletRequest request){
        String requestType = "GET";
        try{
            List<CatWithPriceDTO> data = nativeService.getAllCatWithPrice();
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
        CatNativeMapper2 catNativeMapper2 = new CatNativeMapper2();
        String requestType = "GET";
        try{
            Long id = Long.valueOf(singleAttrDTO.getAttr0());
            CatNative dataRaw = nativeService.getCatById(   id   );
            CatDTO data = catNativeMapper2.catNativeToCatDTOMapper(dataRaw);
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
        CatNativeMapper2 catNativeMapper2 = new CatNativeMapper2();
        CatNative catNative = catNativeMapper2.CatDTOToCatNativeMapper( catDTO );
        ErrorMessageDerived emd = new ErrorMessageDerived();
        String requestType = "POST";
        try{
            boolean result = nativeService.insertCat(catNative);
            if( result ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>(catNative, responseHeader, HttpStatus.OK);
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
        CatNativeMapper2 catNativeMapper2 = new CatNativeMapper2();
        CatNative catNative = catNativeMapper2.CatDTOToCatNativeMapper( catDTO );
        ErrorMessageDerived emd = new ErrorMessageDerived();
        String requestType = "PUT";
        try{
            boolean result = nativeService.updateCat(catNative);
            if( result ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>(catNative, responseHeader, HttpStatus.OK);
            }
            HttpHeaders responseHeader = this.headerCreator.notFoundResponseHeader(request, requestType);
            return new ResponseEntity<>( emd.notFoundError(catNative), responseHeader, HttpStatus.NOT_FOUND);
        }catch( Exception e ){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @Override
    @DeleteMapping
    public ResponseEntity deleteCat(   HttpServletRequest request, @Valid @RequestBody SingleAttrDTO dataReceived   ){
        Long id = Long.valueOf( dataReceived.getAttr0() );
        String requestType = "DELETE";
        try{
            boolean result = nativeService.deleteCat(id);
            if( result ){
                HttpHeaders responseHeader = this.headerCreator.okResponseHeader(request, requestType);
                return new ResponseEntity<>("Delete Operation is succesfull!", responseHeader, HttpStatus.OK);
            }
            ErrorMessageDerived emd = new ErrorMessageDerived();
            HttpHeaders responseHeader = this.headerCreator.conflictResponseHeader(request, requestType);
            return new ResponseEntity<>( emd.notFoundErrorById(id), responseHeader, HttpStatus.CONFLICT);
        }catch( Exception e ){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    

    @Override
    @GetMapping("/excel")
    public ResponseEntity<byte[]> exportAllCatsToExcel(HttpServletRequest request) throws Exception {
        String requestType = "GET";
        try{
            List<CatDTO> data = nativeService.getAllCats();
            return this.excelService.exportAllCatsToExcel(data);
        }catch(Exception e){
            HttpHeaders responseHeader = this.headerCreator.internalServerErrorResponseHeader(request, requestType);
            return new ResponseEntity<>(null, responseHeader, HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
