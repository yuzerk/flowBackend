package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.LoginDto;
import com.flower.xin.common.dto.UserDto;
import com.flower.xin.common.dto.WechatLoginDto;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.util.WechatUtil;
import com.flower.xin.orm.dao.UserDao;
import com.flower.xin.orm.impl.UserDaoService;
import com.flower.xin.orm.impl.UserTokenDaoService;
import com.flower.xin.service.core.UserCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

@Service
public class UserCoreServiceImpl implements UserCoreService {

    @Autowired
    private UserDaoService userDaoService;

    @Autowired
    private UserTokenDaoService userTokenDaoService;

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void loginAndRegister(LoginDto dto) {
        validateLoginAndRegister(dto);
        WechatLoginDto loginDto = WechatUtil.doAuthCodeSession(dto.getBossId(), dto.getCode());
        UserDao userQueryDao = new UserDao();
        userQueryDao.setOpenId(loginDto.getOpenId());
        UserDao userDao = userDaoService.getUser(userQueryDao);
        String token;
        if(userDao == null) {
            userDao = buildLoginDto(dto, loginDto);
            userDaoService.createUser(userDao);
            token = userTokenDaoService.updateToken(userDao.getId());
        } else {
            token = userTokenDaoService.updateToken(userDao.getId());
        }
        dto.setAppToken(token);
        dto.setUid(userDao.getId());
    }

    private UserDao buildLoginDto(LoginDto dto, WechatLoginDto loginDto) {
        UserDao userDao = new UserDao();
        userDao.setBossId(dto.getBossId());
//        userDao.setReferrerId();
        userDao.setNickName(dto.getNickName());
        userDao.setOpenId(loginDto.getOpenId());
//        userDao.setPhoneNum();
//        userDao.setUserType();
        userDao.setCity(dto.getCity());
        userDao.setAvatarUrl(dto.getAvatarUrl());
        userDao.setCountry(dto.getCountry());
        userDao.setProvince(dto.getProvince());
        userDao.setLanguage(dto.getLanguage());
        userDao.setGender(dto.getGender());
//        userDao.setDefaultReceiveId();
        return userDao;
    }

    private void validateLoginAndRegister(LoginDto dto) {
        if(StringUtils.isEmpty(dto.getCode()) || dto.getBossId() == null) {
            throw ErrorMessageEnum.AUTH_TOKEN_ERROR.getSystemException();
        }
    }

    @Override
    public void login(LoginDto dto) {

    }

    @Override
    public void checkToken(LoginDto dto) {

    }

    @Override
    public UserDto getUser(Long userId) {
        return buildUserDto(userDaoService.getUserById(userId));
    }

    private UserDto buildUserDto(UserDao userDao) {
        if(userDao == null) {
            return null;
        }
        UserDto userDto = new UserDto();
        userDto.setId(userDao.getId());
        userDto.setBossId(userDao.getBossId());
        userDto.setReferrerId(userDao.getReferrerId());
        userDto.setNickName(userDao.getNickName());
        userDto.setUserName(userDao.getUserName());
        userDto.setOpenId(userDao.getOpenId());
        userDto.setPhoneNum(userDao.getPhoneNum());
        userDto.setUserType(userDao.getUserType());
        userDto.setCity(userDao.getCity());
        userDto.setAvatarUrl(userDao.getAvatarUrl());
        userDto.setCountry(userDao.getCountry());
        userDto.setProvince(userDao.getProvince());
        userDto.setLanguage(userDao.getLanguage());
        userDto.setGender(userDao.getGender());
        userDto.setDefaultReceiveId(userDao.getDefaultReceiveId());
        userDto.setCreatedAt(userDao.getCreatedAt());
        userDto.setUpdatedAt(userDao.getUpdatedAt());
        return userDto;
    }
}
