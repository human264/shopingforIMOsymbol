package com.example.service;

import com.example.dto.ExcelDto;
import com.example.entity.Order;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
class ExcelServiceTest {

    @Autowired
    ExcelService excelService;

    @Test
    @DisplayName("test01")
    public void test01() {
        List<List<ExcelDto>> excel = excelService.excel();
        for (List<ExcelDto> excelDtos : excel) {
            for (ExcelDto excelDto : excelDtos) {
                System.out.println("excelDto = " + excelDto);
            }
        }
    }


    @Test
    @DisplayName("test02")
    public void test02() {
        excelService.changedOrderStatus();
    }

}