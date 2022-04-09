package com.example.dto;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class ExcelDto {

    private Long id;

    private String shipNo;

    private String imgUrl;

    private String description;

    private Integer qty;

    private String person;

    private String department;

    private String phoneNo;

    private String imgName;

}
