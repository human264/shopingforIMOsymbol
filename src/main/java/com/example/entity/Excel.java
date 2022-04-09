package com.example.entity;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;

@Entity
@Table(name = "excel")
@Getter
@Setter
@ToString
public class Excel extends BaseTimeEntity {

    @Id
    @GeneratedValue
    private Long id;

    private String imgUrl;

    private String description;

    private Integer qty;

    private String person;

    private String department;

    private String phoneNo;

    private String imgName;

    private String oriImgName;

    private String repImgYn;

}
