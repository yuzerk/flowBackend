package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.UserTokenDao;

import java.util.List;

public interface UserTokenDaoMapper {
    int deleteByPrimaryKey(Long id);

    Long insertSelective(UserTokenDao record);

    UserTokenDao selectByPrimaryKey(Long id);

    List<UserTokenDao> selectBySelective(UserTokenDao record);

    int updateByPrimaryKeySelective(UserTokenDao record);

}
