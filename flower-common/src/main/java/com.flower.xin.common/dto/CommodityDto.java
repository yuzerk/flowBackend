package com.flower.xin.common.dto;

import lombok.Data;

import java.util.List;

@Data
public class CommodityDto {
    private Long id;

    private List<Long> ids;

    private String name;

    private String commodityDesc;

    private String detail;

    private String picList;

    private String detailsPicList;

    private Float originalPrice;

    private Float currentPrice;

    private Long classifyId;

    /**
     * 主题id
     */
    private Long subjectId;

    private Integer commodityType;

    private Long bossId;

    private String payload;

    private Integer isValidate;

    private String nameLike;

    private String firstPic;
}
