package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.UserCouponDao;
import com.flower.xin.orm.mapper.UserCouponDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author eiven
 */
@Service
public class UserCouponDaoService {
    @Autowired
    private UserCouponDaoMapper userCouponDaoMapper;

    public List<UserCouponDao> getUserCouponList(UserCouponDao dao) {
        return userCouponDaoMapper.selectBySelective(dao);
    }

    public UserCouponDao getUserCoupon(Long userCouponId) {
        return userCouponDaoMapper.selectByPrimaryKey(userCouponId);
    }

    public Long createUserCoupon(UserCouponDao dao) {
        return userCouponDaoMapper.insertSelective(dao);
    }

    public Integer updateUserCoupon(UserCouponDao dao) {
        return userCouponDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
