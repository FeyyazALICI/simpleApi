package com.simple_api.backend.components.business.service.excelService;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import com.simple_api.backend.components.business.dto.CatDTO;

// Excel imports
import java.io.ByteArrayOutputStream;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;


@Service
public class ExcelService {
    
    public ResponseEntity<byte[]> exportAllCatsToExcel(List<CatDTO> data) throws Exception {
        // Create an Excel workbook
        Workbook workbook = new XSSFWorkbook();

        // preparing sheet name
        Sheet sheet = workbook.createSheet(   "data"   );

        // Create header row
        Row headerRow = sheet.createRow(0);
        headerRow.createCell(0).setCellValue("ID");
        headerRow.createCell(1).setCellValue("NAME");
        headerRow.createCell(2).setCellValue("WEIGHT");

        // Fill data
        int rowNum = 1;
        for (CatDTO item : data) {
            Row row = sheet.createRow(rowNum++);
            row.createCell(0).setCellValue(item.getId());
            row.createCell(1).setCellValue(item.getName());
            row.createCell(2).setCellValue(item.getWeight());
        }

        // Write to byte array output stream
        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        workbook.write(outputStream);
        workbook.close();

        // Set the response headers
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(org.springframework.http.MediaType.APPLICATION_OCTET_STREAM);
        // preparing file name
        Date currenDate = new Date(System.currentTimeMillis());
        String sdfPattern = "dd.MM.yyyy";
        SimpleDateFormat sdf = new SimpleDateFormat(sdfPattern);
        String dateString = sdf.format(currenDate);
        StringBuilder stb = new StringBuilder();
        stb.append("AllCats");
        stb.append("_");
        stb.append(dateString);
        stb.append(".xlsx");
        String fileName = stb.toString();
        
        headers.setContentDispositionFormData("attachment", fileName);
        headers.setContentLength(outputStream.size());

        return new ResponseEntity<>(outputStream.toByteArray(), headers, HttpStatus.OK);
    }
}
