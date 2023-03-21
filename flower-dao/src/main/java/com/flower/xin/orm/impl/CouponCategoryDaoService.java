package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.CouponCategoryDao;
import com.flower.xin.orm.mapper.CouponCategoryDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CouponCategoryDaoService {
    @Autowired
    private CouponCategoryDaoMapper couponCategoryDaoMapper;


    public List<CouponCategoryDao> getCouponCategoryList(CouponCategoryDao dao) {
        return couponCategoryDaoMapper.selectBySelective(dao);
    }

    public CouponCategoryDao getCouponCategory(Long userCouponId) {
        return couponCategoryDaoMapper.selectByPrimaryKey(userCouponId);
    }

    public Integer createCouponCategory(CouponCategoryDao dao) {
        return couponCategoryDaoMapper.insertSelective(dao);
    }

    public Integer updateCouponCategory(CouponCategoryDao dao) {
        return couponCategoryDaoMapper.updateByPrimaryKeySelective(dao);
    }

}
