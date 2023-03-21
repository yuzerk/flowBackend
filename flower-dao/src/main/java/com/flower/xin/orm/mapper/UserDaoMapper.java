package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.UserDao;

import java.util.List;

public interface UserDaoMapper {
    int deleteByPrimaryKey(Long id);

    Long insertSelective(UserDao record);

    UserDao selectByPrimaryKey(Long id);

    List<UserDao> selectBySelective(UserDao record);

    int updateByPrimaryKeySelective(UserDao record);
}
