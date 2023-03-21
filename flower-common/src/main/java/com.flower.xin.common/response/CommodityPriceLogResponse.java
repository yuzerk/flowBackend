package com.flower.xin.common.response;

import com.flower.xin.common.response.base.Response;
import lombok.Data;

import java.util.Date;

@Data
public class CommodityPriceLogResponse {
    private Long id;

    private Long commodityId;

    private Float originalPrice;

    private Float currentPrice;

    private Date createdAt;

    private Date updatedAt;
}
