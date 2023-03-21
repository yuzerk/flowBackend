package com.flower.xin.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommoditySubjectDto {
    private Long id;

    private String name;

    private Integer type;

    private Integer score;

    private String picList;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;
}
