package com.flower.xin.service.core;

import com.flower.xin.common.dto.OrderCommodityDto;
import com.flower.xin.common.query.OrderCommodityQuery;

import java.util.List;

public interface OrderCommodityCoreService {
    void createOrderCommodity(OrderCommodityDto dto);

    List<OrderCommodityDto> getOrderCommodities(OrderCommodityQuery query);
}
