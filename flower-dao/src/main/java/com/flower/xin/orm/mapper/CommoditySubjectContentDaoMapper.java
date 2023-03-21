package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.CommodityPriceLogDao;
import com.flower.xin.orm.dao.CommoditySubjectContentDao;

import java.util.List;

public interface CommoditySubjectContentDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CommoditySubjectContentDao record);

    CommoditySubjectContentDao selectByPrimaryKey(Long id);

    List<CommoditySubjectContentDao> selectBySelective(CommoditySubjectContentDao record);

    int updateByPrimaryKeySelective(CommoditySubjectContentDao record);
}
