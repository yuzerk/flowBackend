package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.CouponCategoryDao;

import java.util.List;

/**
 * @author eiven
 */
public interface CouponCategoryDaoMapper {
    int deleteByPrimaryKey(Long id);

    int insertSelective(CouponCategoryDao record);

    CouponCategoryDao selectByPrimaryKey(Long id);

    List<CouponCategoryDao> selectBySelective(CouponCategoryDao record);

    int updateByPrimaryKeySelective(CouponCategoryDao record);
}
