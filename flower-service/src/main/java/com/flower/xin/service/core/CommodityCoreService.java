package com.flower.xin.service.core;

import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.query.CommodityQuery;
import com.github.pagehelper.PageInfo;

import java.util.List;

public interface CommodityCoreService {
    PageInfo<CommodityDto> getCommodities(CommodityQuery query);

    List<CommodityDto> getCommoditiesBySubject(CommodityQuery query);

    PageInfo<CommodityDto> searchCommodities(CommodityQuery query);

    void createCommodities(CommodityDto dto);

    void updateCommodity(CommodityDto dto);

    List<CommodityDto> getCommoditiesNotPage(CommodityQuery query);
}
