package com.flower.xin.orm.mapper;

import com.flower.xin.orm.dao.UserReceiveAddressDao;

import java.util.List;

public interface UserReceiveAddressDaoMapper {
    int deleteByPrimaryKey(Long id);

    Long insertSelective(UserReceiveAddressDao record);

    UserReceiveAddressDao selectByPrimaryKey(Long id);

    List<UserReceiveAddressDao> selectBySelective(UserReceiveAddressDao dao);

    int updateByPrimaryKeySelective(UserReceiveAddressDao record);
}
