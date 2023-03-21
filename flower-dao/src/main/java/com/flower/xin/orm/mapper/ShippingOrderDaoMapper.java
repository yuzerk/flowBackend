package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.OrderDao;
import com.flower.xin.orm.dao.ShippingOrderDao;

import java.util.List;

public interface ShippingOrderDaoMapper {
    int insertSelective(ShippingOrderDao record);

    ShippingOrderDao selectByPrimaryKey(Long id);

    List<ShippingOrderDao> selectBySelective(ShippingOrderDao record);

    int updateByPrimaryKeySelective(ShippingOrderDao record);
}
