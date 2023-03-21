package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectContentParam extends RequestParam {
    private Long id;

    private Long commoditySubjectId;

    private List<Long> commoditySubjectIds;

    private Long commodityId;

    private Date createdAt;

    private Date updatedAt;
}
