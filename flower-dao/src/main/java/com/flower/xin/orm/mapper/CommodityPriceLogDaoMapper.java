package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.CommodityDao;
import com.flower.xin.orm.dao.CommodityPriceLogDao;

import java.util.List;

public interface CommodityPriceLogDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CommodityPriceLogDao record);

    CommodityPriceLogDao selectByPrimaryKey(Long id);

    List<CommodityPriceLogDao> selectBySelective(CommodityPriceLogDao record);

    int updateByPrimaryKeySelective(CommodityPriceLogDao record);
}
