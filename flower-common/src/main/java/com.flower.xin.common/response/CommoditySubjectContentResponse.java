package com.flower.xin.common.response;

import com.flower.xin.common.response.base.Response;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectContentResponse {
    private Long id;

    private Long commoditySubjectId;

    private List<Long> commoditySubjectIds;

    private Long commodityId;

    private Date createdAt;

    private Date updatedAt;
}
