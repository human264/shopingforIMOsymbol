package com.example.controller;

import com.example.dto.ExcelDto;
import com.example.service.ExcelDownService;
import com.example.service.ExcelService;
import lombok.RequiredArgsConstructor;
import org.apache.tomcat.util.http.fileupload.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.List;


@Controller
@RequiredArgsConstructor
public class ExcelDownLoadController {


    private final ExcelService excelService;

    @GetMapping("/excelDown")
    public String main() throws Exception {
        return "imoSymbol/excelList";
    }


    @GetMapping("/excelDown/IMO_SYMBOL.xlsx")
    public void downloadExcel(HttpServletResponse response) throws IOException {
        response.setContentType("application/octet-stream");
        response.setHeader("Content-Disposition", "attachment; filename=/excelDown/IMO_SYMBOL.xlsx");

        List<List<ExcelDto>> excel = excelService.excel();
        System.out.println(excel);
        ExcelDto.staticExcelDTO = excel;
        ByteArrayInputStream stream = ExcelDownService.contactListToExcelFile(createTestData());
        IOUtils.copy(stream, response.getOutputStream());

    }

    private List<List<ExcelDto>> createTestData(){
        return ExcelDto.staticExcelDTO;
    }


}
