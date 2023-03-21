package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommodityParam extends RequestParam {
    private Long id;

    private List<Long> ids;

    private String name;

    private String commodityDesc;

    private String detail;

    private List<String> picList;

    private String detailsPicList;

    private Float originalPrice;

    private Float currentPrice;

    /**
     * 分类id
     */
    private Long classifyId;

    /**
     * 主题id
     */
    private Long subjectId;

    private String commodityType;

    private Long bossId;

    private String payload;

    private Integer isValidate;

    private String nameLike;
}
