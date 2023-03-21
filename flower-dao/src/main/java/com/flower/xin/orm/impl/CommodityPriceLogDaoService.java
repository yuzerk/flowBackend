package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommodityPriceLogDao;
import com.flower.xin.orm.mapper.CommodityPriceLogDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CommodityPriceLogDaoService {

    @Autowired
    private CommodityPriceLogDaoMapper commodityPriceLogDaoMapper;

    public List<CommodityPriceLogDao> getCommodityPriceLogList(CommodityPriceLogDao dao) {
        return commodityPriceLogDaoMapper.selectBySelective(dao);
    }

    public CommodityPriceLogDao getCommodityPriceLog(Long userCouponId) {
        return commodityPriceLogDaoMapper.selectByPrimaryKey(userCouponId);
    }

    public Integer createCommodityPriceLog(CommodityPriceLogDao dao) {
        return commodityPriceLogDaoMapper.insertSelective(dao);
    }

    public Integer updateCommodityPriceLog(CommodityPriceLogDao dao) {
        return commodityPriceLogDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
