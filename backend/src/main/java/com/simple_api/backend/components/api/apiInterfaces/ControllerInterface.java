package com.simple_api.backend.components.api.apiInterfaces;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;

import com.simple_api.backend.components.business.dto.CatDTO;
import com.simple_api.backend.components.business.dto.SingleAttrDTO;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;

public interface ControllerInterface {
    ResponseEntity getAllCats(   HttpServletRequest request   );
    ResponseEntity getCatById(   HttpServletRequest request, @Valid @RequestBody SingleAttrDTO singleAttrDTO   );
    ResponseEntity insertCat(   HttpServletRequest request, @Valid @RequestBody CatDTO catDTO   );
    ResponseEntity updateCat(   HttpServletRequest request, @Valid @RequestBody CatDTO catDTO   );
    ResponseEntity deleteCat(   HttpServletRequest request, @Valid @RequestBody SingleAttrDTO dataReceived   );
    ResponseEntity<byte[]> exportAllCatsToExcel(HttpServletRequest request) throws Exception;
}
