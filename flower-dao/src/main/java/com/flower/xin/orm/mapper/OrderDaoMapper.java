package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.OrderCommodityDao;
import com.flower.xin.orm.dao.OrderDao;

import java.util.List;

public interface OrderDaoMapper {
    int insertSelective(OrderDao record);

    OrderDao selectByPrimaryKey(Long id);

    List<OrderDao> selectBySelective(OrderDao record);

    int updateByPrimaryKeySelective(OrderDao record);
}
