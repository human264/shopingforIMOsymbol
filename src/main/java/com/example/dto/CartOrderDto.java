package com.example.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CartOrderDto {

    private Long cartItemId;

    private String shipNo;

    private List<CartOrderDto> cartOrderDtoList;

}