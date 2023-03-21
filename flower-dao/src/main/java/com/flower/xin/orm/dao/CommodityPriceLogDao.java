package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommodityPriceLogDao implements Serializable {
    private Long id;

    private Long commodityId;

    private Integer originalPrice;

    private Integer currentPrice;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
