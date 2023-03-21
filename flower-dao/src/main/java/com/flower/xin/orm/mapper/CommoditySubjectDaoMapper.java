package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.CommoditySubjectDao;

import java.util.List;

public interface CommoditySubjectDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CommoditySubjectDao record);

    CommoditySubjectDao selectByPrimaryKey(Long id);

    List<CommoditySubjectDao> selectBySelective(CommoditySubjectDao record);

    int updateByPrimaryKeySelective(CommoditySubjectDao record);
}
