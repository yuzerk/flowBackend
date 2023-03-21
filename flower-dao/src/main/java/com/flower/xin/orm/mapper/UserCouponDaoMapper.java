package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.UserCouponDao;

import java.util.List;

public interface UserCouponDaoMapper {
    int deleteByPrimaryKey(Long id);

    Long insertSelective(UserCouponDao record);

    UserCouponDao selectByPrimaryKey(Long id);

    List<UserCouponDao> selectBySelective(UserCouponDao record);

    int updateByPrimaryKeySelective(UserCouponDao record);
}
