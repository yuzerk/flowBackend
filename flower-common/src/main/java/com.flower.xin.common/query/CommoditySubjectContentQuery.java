package com.flower.xin.common.query;

import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectContentQuery {
    private Long id;

    private Long commoditySubjectId;

    private List<Long> commoditySubjectIds;

    private Long commodityId;

    private Date createdAt;

    private Date updatedAt;
}
