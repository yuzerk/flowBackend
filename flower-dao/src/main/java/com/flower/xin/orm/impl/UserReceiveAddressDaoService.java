package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.UserDao;
import com.flower.xin.orm.dao.UserReceiveAddressDao;
import com.flower.xin.orm.mapper.UserReceiveAddressDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

/**
 * @author eiven
 */
@Service
public class UserReceiveAddressDaoService {

    @Autowired
    private UserReceiveAddressDaoMapper userReceiveAddressDaoMapper;

    public List<UserReceiveAddressDao> getUserReceiveAddressList(UserReceiveAddressDao dao) {
        return userReceiveAddressDaoMapper.selectBySelective(dao);
    }

    public UserReceiveAddressDao getUserReceiveAddress(Long userReceiveAddressId) {
        return userReceiveAddressDaoMapper.selectByPrimaryKey(userReceiveAddressId);
    }

    public Long createUserReceiveAddress(UserReceiveAddressDao dao) {
        return userReceiveAddressDaoMapper.insertSelective(dao);
    }

    public Integer updateUserReceiveAddress(UserReceiveAddressDao dao) {
        return userReceiveAddressDaoMapper.updateByPrimaryKeySelective(dao);
    }
}
