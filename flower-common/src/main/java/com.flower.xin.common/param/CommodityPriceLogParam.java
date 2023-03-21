package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;

@Data
public class CommodityPriceLogParam extends RequestParam {
    private Long id;

    private Long commodityId;

    private Float originalPrice;

    private Float currentPrice;

    private Date createdAt;

    private Date updatedAt;
}
