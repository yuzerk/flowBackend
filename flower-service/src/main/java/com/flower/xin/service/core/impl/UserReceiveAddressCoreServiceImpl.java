package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.UserReceiveAddressDto;
import com.flower.xin.orm.dao.UserDao;
import com.flower.xin.orm.dao.UserReceiveAddressDao;
import com.flower.xin.orm.impl.UserDaoService;
import com.flower.xin.orm.impl.UserReceiveAddressDaoService;
import com.flower.xin.service.core.UserReceiveAddressCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author eiven
 */
@Service
public class UserReceiveAddressCoreServiceImpl implements UserReceiveAddressCoreService {

    @Autowired
    private UserReceiveAddressDaoService userReceiveAddressDaoService;

    @Autowired
    private UserDaoService userDaoService;

    @Override
    public void createAddress(UserReceiveAddressDto dto) {
        userReceiveAddressDaoService.createUserReceiveAddress(buildUserReceiveAddressDao(dto));
    }

    @Override
    public void updateAddress(UserReceiveAddressDto dto) {
        userReceiveAddressDaoService.updateUserReceiveAddress(buildUserReceiveAddressDao(dto));
    }

    @Override
    public void setDefaultAddress(UserReceiveAddressDto dto) {
        userDaoService.updateUser(buildUserDao(dto));
    }

    private UserDao buildUserDao(UserReceiveAddressDto dto) {
        UserDao userDao = new UserDao();
        userDao.setId(dto.getUserId());
        userDao.setDefaultReceiveId(dto.getId());
        return userDao;
    }

    private UserReceiveAddressDao buildUserReceiveAddressDao(UserReceiveAddressDto dto) {
        UserReceiveAddressDao userReceiveAddressDao = new UserReceiveAddressDao();
        userReceiveAddressDao.setId(dto.getId());
        userReceiveAddressDao.setUserId(dto.getUserId());
        userReceiveAddressDao.setCustomerName(dto.getCustomerName());
        userReceiveAddressDao.setCustomerPhone(dto.getCustomerPhone());
        userReceiveAddressDao.setProvince(dto.getProvince());
        userReceiveAddressDao.setSlCity(dto.getSlCity());
        userReceiveAddressDao.setTlCity(dto.getTlCity());
        userReceiveAddressDao.setStreet(dto.getStreet());
        userReceiveAddressDao.setAddressDetail(dto.getAddressDetail());
        userReceiveAddressDao.setIsValidate(dto.getIsValidate());
        return userReceiveAddressDao;
    }
}
