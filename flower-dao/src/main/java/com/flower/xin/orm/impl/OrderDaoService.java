package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.OrderDao;
import com.flower.xin.orm.mapper.OrderDaoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class OrderDaoService {

    @Autowired
    private OrderDaoMapper orderDaoMapper;

    public PageInfo<OrderDao> getOrderListByPage(OrderDao dao) {
        PageHelper.startPage(dao.getPage(),dao.getLimit());
        List<OrderDao> list = orderDaoMapper.selectBySelective(dao);
        return new PageInfo<>(list);
    }

    public OrderDao getOrder(Long id) {
        return orderDaoMapper.selectByPrimaryKey(id);
    }

    public Integer createOrder(OrderDao dao) {
        return orderDaoMapper.insertSelective(dao);
    }

    public Integer updateOrder(OrderDao dao) {
        return orderDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
