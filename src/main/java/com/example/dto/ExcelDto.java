package com.example.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@ToString
public class ExcelDto {

    public static List<List<ExcelDto>> staticExcelDTO = new ArrayList<>();

    private Long id;

    private String shipNo;

    private String imgUrl;

    private String description;

    private Integer qty;

    private String person;

    private String department;

    private String phoneNo;


}
