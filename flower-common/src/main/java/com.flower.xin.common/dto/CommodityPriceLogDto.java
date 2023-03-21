package com.flower.xin.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class CommodityPriceLogDto {
    private Long id;

    private Long commodityId;

    private Float originalPrice;

    private Float currentPrice;

    private Date createdAt;

    private Date updatedAt;
}
