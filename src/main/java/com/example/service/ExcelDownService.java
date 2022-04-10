package com.example.service;

import com.example.dto.ExcelDto;
import lombok.RequiredArgsConstructor;
import lombok.extern.java.Log;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.util.IOUtils;
import org.apache.poi.xssf.usermodel.*;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.*;
import java.util.List;

@Service
@RequiredArgsConstructor
@Transactional
@Log
public class ExcelDownService {

    @Value("${excelIMOFile}")
    private static String excelIMOFolder = "/Users/jounghoyeun/JAVAProjects/shop";
    private final static String imoFile = excelIMOFolder + "/IMO_SYMBOL.xlsx";

    public static ByteArrayInputStream contactListToExcelFile(List<List<ExcelDto>> excelDtos) {

        try {
            FileInputStream file = new FileInputStream(imoFile);
            Workbook workbook = new XSSFWorkbook(file);
            Sheet sheet = workbook.getSheet("LIST");
            Row row = null;

            int index = 0;
            for (List<ExcelDto> excelDto : excelDtos) {

                for (ExcelDto dto : excelDto) {
                    String filePath = dto.getImgUrl();
                    InputStream is = new FileInputStream(filePath);

                    byte[] bytes = IOUtils.toByteArray(is);

                    int picIdx = workbook.addPicture(bytes, XSSFWorkbook.PICTURE_TYPE_PNG);
                    is.close();

                    sheet.createRow(4 + index);
                    index ++;


                    XSSFCreationHelper helper = (XSSFCreationHelper) workbook.getCreationHelper();
                    XSSFDrawing drawing = (XSSFDrawing) sheet.createDrawingPatriarch();
                    XSSFClientAnchor anchor = helper.createClientAnchor();

                    // 이미지 출력할 cell 위치
                    anchor.setCol1(1);
                    anchor.setRow1(4 + index);
                    // 이미지 그리기
                    XSSFPicture pic = drawing.createPicture(anchor, picIdx);
                    pic.resize(0.8);

                }
            }
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            workbook.write(outputStream);
            return new ByteArrayInputStream(outputStream.toByteArray());


        } catch (IOException ex) {
            ex.printStackTrace();
            return null;
        }
    }


}
