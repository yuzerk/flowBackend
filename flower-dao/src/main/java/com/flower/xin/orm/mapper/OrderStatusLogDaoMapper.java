package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.OrderStatusLogDao;

public interface OrderStatusLogDaoMapper {
    int insertSelective(OrderStatusLogDao record);

    OrderStatusLogDao selectByPrimaryKey(Long id);

    int updateByPrimaryKeySelective(OrderStatusLogDao record);
}
