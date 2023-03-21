package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.UserCouponDto;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.util.MoneyUtil;
import com.flower.xin.common.util.TimeUtil;
import com.flower.xin.orm.dao.CouponCategoryDao;
import com.flower.xin.orm.dao.UserCouponDao;
import com.flower.xin.orm.impl.CouponCategoryDaoService;
import com.flower.xin.orm.impl.UserCouponDaoService;
import com.flower.xin.service.core.UserCouponCoreService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@Service
public class UserCouponCoreServiceImpl implements UserCouponCoreService {

    @Autowired
    private UserCouponDaoService userCouponDaoService;

    @Autowired
    private CouponCategoryDaoService couponCategoryDaoService;

    @Override
    public void createUserCoupon(UserCouponDto dto) throws ServiceException {
        CouponCategoryDao couponCategoryDao = couponCategoryDaoService.getCouponCategory(dto.getCouponCategoryId());
        if(couponCategoryDao == null) {
            throw ErrorMessageEnum.HAVE_NO_COUPON_TYPE.getServiceException();
        }

        UserCouponDao userCouponDao = buildUserCouponDao(couponCategoryDao, dto);
        userCouponDaoService.createUserCoupon(userCouponDao);
    }

    @Override
    public void updateUserCoupon(UserCouponDto dto) {
        userCouponDaoService.updateUserCoupon(buildUserCouponDao(dto));
    }

    @Override
    public List<UserCouponDto> getByUid(String uid) {
        UserCouponDao dao = new UserCouponDao();
        dao.setUserId(Long.parseLong(uid));
        List<UserCouponDao> userCouponDaos = userCouponDaoService.getUserCouponList(dao);
        for(UserCouponDao userCouponDao : userCouponDaos) {
            checkValidate(userCouponDao);
        }
        return userCouponDaos.stream()
                .map(this::buildUserCouponDto).collect(Collectors.toList());
    }

    @Override
    public UserCouponDto getById(Long id) {
        UserCouponDao userCouponDao = userCouponDaoService.getUserCoupon(id);
        if(userCouponDao == null){
            return null;
        }
        checkValidate(userCouponDao);
        return buildUserCouponDto(userCouponDao);
    }

    @Override
    public void validateUserCopon(Long id) {
        UserCouponDao dao = new UserCouponDao();
        dao.setId(id);
        dao.setIsValidate(ValidateEnum.VALIDATE.getCode());
        userCouponDaoService.updateUserCoupon(dao);
    }

    @Override
    public void invalidateUserCopon(Long id) {
        UserCouponDao dao = new UserCouponDao();
        dao.setId(id);
        dao.setIsValidate(ValidateEnum.INVALIDATE.getCode());
        userCouponDaoService.updateUserCoupon(dao);
    }

    private void checkValidate(UserCouponDao userCouponDao) {
        if(userCouponDao.getIsValidate().equals(ValidateEnum.VALIDATE.getCode())) {
            if(userCouponDao.getExpireAt().getTime() < System.currentTimeMillis()) {
                userCouponDao.setIsValidate(ValidateEnum.INVALIDATE.getCode());
                userCouponDaoService.updateUserCoupon(userCouponDao);
            }
        }
    }

    private UserCouponDao buildUserCouponDao(CouponCategoryDao couponCategoryDao, UserCouponDto dto) {
        UserCouponDao userCouponDao = new UserCouponDao();
        userCouponDao.setUserId(dto.getUserId());
        userCouponDao.setCouponCategoryId(couponCategoryDao.getId());
        userCouponDao.setDiscounts(couponCategoryDao.getDiscounts());
        userCouponDao.setCouponType(couponCategoryDao.getCouponType());
        userCouponDao.setIsValidate(ValidateEnum.VALIDATE.getCode());
        userCouponDao.setExpireAt(TimeUtil.getDateAddMinutes(new Date(), couponCategoryDao.getExpireTime().intValue()));
        return userCouponDao;
    }


    private UserCouponDao buildUserCouponDao(UserCouponDto dto) {
        UserCouponDao userCouponDao = new UserCouponDao();
        userCouponDao.setId(dto.getId());
        userCouponDao.setUserId(dto.getUserId());
        userCouponDao.setCouponCategoryId(dto.getCouponCategoryId());
        userCouponDao.setDiscounts(MoneyUtil.yuanToFen(dto.getDiscounts()));
        userCouponDao.setCouponType(dto.getCouponType());
        userCouponDao.setIsValidate(dto.getIsValidate());
        return userCouponDao;
    }

    private UserCouponDto buildUserCouponDto(UserCouponDao dao) {
        UserCouponDto dto = new UserCouponDto();
        BeanUtils.copyProperties(dao, dto);
        dto.setDiscounts(MoneyUtil.fenToYuan(dao.getDiscounts()));
        return dto;
    }
}
