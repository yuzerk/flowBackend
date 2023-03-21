package com.flower.xin.common.dto;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectContentDto {
    private Long id;

    private Long commoditySubjectId;

    private List<Long> commoditySubjectIds;

    private Long commodityId;

    private Date createdAt;

    private Date updatedAt;
}
