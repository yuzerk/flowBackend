package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommodityDao;
import com.flower.xin.orm.dao.OrderCommodityDao;
import com.flower.xin.orm.mapper.CommodityDaoMapper;
import com.flower.xin.orm.mapper.OrderCommodityDaoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderCommodityDaoService {

    @Autowired
    private OrderCommodityDaoMapper orderCommodityDaoMapper;

    public List<OrderCommodityDao> getOrderCommodityList(OrderCommodityDao dao) {
        return orderCommodityDaoMapper.selectBySelective(dao);
    }

    public Integer createOrderCommodity(OrderCommodityDao dao) {
        return orderCommodityDaoMapper.insertSelective(dao);
    }

    public Integer updateOrderCommodity(OrderCommodityDao dao) {
        return orderCommodityDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
