package com.flower.xin.service.core;

import com.flower.xin.common.dto.CommodityClassifyDto;
import com.flower.xin.common.query.CommodityClassifyQuery;

import java.util.List;

public interface CommodityClassifyCoreService {
    List<CommodityClassifyDto> getCommodityClassify(CommodityClassifyQuery query);

    void createCommodityClassify(CommodityClassifyDto dto);

    void updateCommodityClassify(CommodityClassifyDto dto);
}
