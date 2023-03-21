package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.OrderCommodityDao;

import java.util.List;

public interface OrderCommodityDaoMapper {
    int insertSelective(OrderCommodityDao record);

    OrderCommodityDao selectByPrimaryKey(Long id);

    List<OrderCommodityDao> selectBySelective(OrderCommodityDao record);

    int updateByPrimaryKeySelective(OrderCommodityDao record);
}
