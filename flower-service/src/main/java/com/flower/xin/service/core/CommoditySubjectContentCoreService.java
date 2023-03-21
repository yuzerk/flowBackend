package com.flower.xin.service.core;

import com.flower.xin.common.dto.CommoditySubjectContentDto;
import com.flower.xin.common.dto.CommoditySubjectDto;
import com.flower.xin.common.query.CommoditySubjectContentQuery;
import com.flower.xin.common.query.CommoditySubjectQuery;

import java.util.List;

public interface CommoditySubjectContentCoreService {
    List<CommoditySubjectContentDto> getCommoditySubject(CommoditySubjectContentQuery query);

    void createCommoditySubject(CommoditySubjectContentDto dto);

    void updateCommoditySubject(CommoditySubjectContentDto dto);
}
