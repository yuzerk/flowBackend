package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.CommodityClassifyDao;

import java.util.List;

public interface CommodityClassifyDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CommodityClassifyDao record);

    CommodityClassifyDao selectByPrimaryKey(Long id);

    List<CommodityClassifyDao> selectBySelective(CommodityClassifyDao record);

    int updateByPrimaryKeySelective(CommodityClassifyDao record);
}
