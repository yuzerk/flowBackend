package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommodityDao;
import com.flower.xin.orm.dao.ShippingOrderDao;
import com.flower.xin.orm.mapper.CommodityDaoMapper;
import com.flower.xin.orm.mapper.ShippingOrderDaoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.List;

@Service
public class ShippingOrderDaoService {

    @Autowired
    private ShippingOrderDaoMapper shippingOrderDaoMapper;

    public List<ShippingOrderDao> getShippingOrderList(ShippingOrderDao dao) {
        return shippingOrderDaoMapper.selectBySelective(dao);
    }

    public ShippingOrderDao getShippingOrder(Long id) {
        return shippingOrderDaoMapper.selectByPrimaryKey(id);
    }

    public Integer createShippingOrder(ShippingOrderDao dao) {
        return shippingOrderDaoMapper.insertSelective(dao);
    }

    public Integer updateShippingOrder(ShippingOrderDao dao) {
        return shippingOrderDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
