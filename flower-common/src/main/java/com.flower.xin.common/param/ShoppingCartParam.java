package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class ShoppingCartParam extends RequestParam {
    private Long id;

    private List<Long> ids;

    private Long commodityId;

    private Integer commodityCount;

    private Date createdAt;
}
