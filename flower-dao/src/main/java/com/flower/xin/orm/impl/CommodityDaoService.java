package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CommodityDao;
import com.flower.xin.orm.mapper.CommodityDaoMapper;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.Collections;
import java.util.List;

@Service
public class CommodityDaoService {

    @Autowired
    private CommodityDaoMapper commodityDaoMapper;

    public List<CommodityDao> getCommodityList(CommodityDao dao) {
        return commodityDaoMapper.selectBySelective(dao);
    }

    public PageInfo<CommodityDao> getCommodityListByPage(CommodityDao dao) {
        PageHelper.startPage(dao.getPage(),dao.getLimit());
        List<CommodityDao> list = commodityDaoMapper.selectBySelective(dao);
        return new PageInfo<>(list);
    }

    public PageInfo<CommodityDao> getCommodityListByNameLike(CommodityDao dao) {
        PageHelper.startPage(dao.getPage(),dao.getLimit());
        String name = dao.getName();
        if(StringUtils.isEmpty(name)) {
            return new PageInfo<>();
        }
        dao.setName("*"+name+"*");
        List<CommodityDao> list = commodityDaoMapper.selectBySelective(dao);
        return new PageInfo<>(list);

    }

    public CommodityDao getCommodity(Long userCouponId) {
        return commodityDaoMapper.selectByPrimaryKey(userCouponId);
    }

    public Integer createCommodity(CommodityDao dao) {
        return commodityDaoMapper.insertSelective(dao);
    }

    public Integer updateCommodity(CommodityDao dao) {
        return commodityDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
