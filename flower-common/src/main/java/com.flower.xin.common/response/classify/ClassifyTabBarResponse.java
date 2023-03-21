package com.flower.xin.common.response.classify;

import com.flower.xin.common.dto.CommodityClassifyDto;
import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.response.CommodityClassifyResponse;
import com.flower.xin.common.response.CommodityResponse;
import lombok.Data;

import java.util.List;

@Data
public class ClassifyTabBarResponse {

    // 分类
    private List<ClassifyAndSubjectResponse> classifyAndSubjectResponses;

    // 商品
    private List<CommodityResponse> commodityDtos;
}
