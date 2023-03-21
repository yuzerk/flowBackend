package com.flower.xin.orm.impl;

import com.flower.xin.common.util.TimeUtil;
import com.flower.xin.orm.dao.UserTokenDao;
import com.flower.xin.orm.mapper.UserTokenDaoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Service
public class UserTokenDaoService {
    @Autowired
    private UserTokenDaoMapper userTokenDaoMapper;

    /**
     * token过期时间，暂定3小时。单位：分钟
     */
    private final Integer EXPIRE_TIME = 3 * 60;

    public UserTokenDao getToken(Long userId) {
        UserTokenDao userTokenDao = new UserTokenDao();
        userTokenDao.setUserId(userId);
        List<UserTokenDao> tokenList = userTokenDaoMapper.selectBySelective(userTokenDao);
        if (CollectionUtils.isEmpty(tokenList)) {
            return null;
        }
        return tokenList.get(0);
    }

    public String updateToken(Long userId) {
        UserTokenDao userTokenDao = new UserTokenDao();
        userTokenDao.setUserId(userId);
        String token = UUID.randomUUID().toString();
        userTokenDao.setToken(token);
        userTokenDao.setExpireAt(TimeUtil.getDateAddMinutes(new Date(), EXPIRE_TIME));
        userTokenDaoMapper.updateByPrimaryKeySelective(userTokenDao);
        return token;
    }

    public String createToken(Long userId) {
        UserTokenDao userTokenDao = new UserTokenDao();
        userTokenDao.setUserId(userId);
        String token = UUID.randomUUID().toString();
        userTokenDao.setToken(token);
        userTokenDao.setExpireAt(TimeUtil.getDateAddMinutes(new Date(), EXPIRE_TIME));
        userTokenDaoMapper.insertSelective(userTokenDao);
        return token;
    }
}
