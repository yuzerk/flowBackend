package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommodityClassifyDao;
import com.flower.xin.orm.mapper.CommodityClassifyDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CommodityClassifyDaoService {

    @Autowired
    private CommodityClassifyDaoMapper commodityClassifyDaoMapper;

    public List<CommodityClassifyDao> getCommodityClassifyList(CommodityClassifyDao dao) {
        return commodityClassifyDaoMapper.selectBySelective(dao);
    }

    public CommodityClassifyDao getCommodityClassify(Long userCouponId) {
        return commodityClassifyDaoMapper.selectByPrimaryKey(userCouponId);
    }

    public Integer createCommodityClassify(CommodityClassifyDao dao) {
        return commodityClassifyDaoMapper.insertSelective(dao);
    }

    public Integer updateCommodityClassify(CommodityClassifyDao dao) {
        return commodityClassifyDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
