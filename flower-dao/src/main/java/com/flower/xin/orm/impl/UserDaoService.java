package com.flower.xin.orm.impl;

import com.flower.xin.orm.dao.UserDao;
import com.flower.xin.orm.mapper.UserDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class UserDaoService {
    @Autowired
    private UserDaoMapper userDaoMapper;

    public UserDao getUser(UserDao dao) {
        List<UserDao> tokenList = userDaoMapper.selectBySelective(dao);
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }
        return tokenList.get(0);
    }

    public Long createUser(UserDao dao) {
        return userDaoMapper.insertSelective(dao);
    }

    public void updateUser(UserDao dao) {
        userDaoMapper.updateByPrimaryKeySelective(dao);
    }

    public UserDao getUserById(Long id) {
        return userDaoMapper.selectByPrimaryKey(id);
    }
}
