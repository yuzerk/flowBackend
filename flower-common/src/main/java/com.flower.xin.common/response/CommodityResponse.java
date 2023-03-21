package com.flower.xin.common.response;

import com.flower.xin.common.response.base.Response;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommodityResponse{
    private Long id;

    private String name;

    private String commodityDesc;

    private String detail;

    private List<String> picList;

    private String detailsPicList;

    private Float originalPrice;

    private Float currentPrice;

    private Long classifyId;

    private Integer commodityType;

    private Long bossId;

    private String payload;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;

    private String firstPic;
}
