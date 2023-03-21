package com.flower.xin.service.core;

import com.flower.xin.common.dto.CommoditySubjectDto;
import com.flower.xin.common.query.CommoditySubjectQuery;

import java.util.List;

public interface CommoditySubjectCoreService {
    List<CommoditySubjectDto> getCommoditySubject(CommoditySubjectQuery query);

    void createCommoditySubject(CommoditySubjectDto dto);

    void updateCommoditySubject(CommoditySubjectDto dto);
}
