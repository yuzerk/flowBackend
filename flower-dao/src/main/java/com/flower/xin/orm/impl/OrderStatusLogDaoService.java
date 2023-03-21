package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommodityDao;
import com.flower.xin.orm.dao.OrderStatusLogDao;
import com.flower.xin.orm.mapper.CommodityDaoMapper;
import com.flower.xin.orm.mapper.OrderStatusLogDaoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class OrderStatusLogDaoService {

    @Autowired
    private OrderStatusLogDaoMapper orderStatusLogDaoMapper;

    public Integer createOrderStatusLog(OrderStatusLogDao dao) {
        return orderStatusLogDaoMapper.insertSelective(dao);
    }
}
