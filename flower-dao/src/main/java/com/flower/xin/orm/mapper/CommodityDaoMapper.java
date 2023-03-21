package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.CommodityDao;

import java.util.List;

public interface CommodityDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CommodityDao record);

    CommodityDao selectByPrimaryKey(Long id);

    List<CommodityDao> selectBySelective(CommodityDao record);

    List<CommodityDao> selectByNameLike(CommodityDao record);

    int updateByPrimaryKeySelective(CommodityDao record);
}
