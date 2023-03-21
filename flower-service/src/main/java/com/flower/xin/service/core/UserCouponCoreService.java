package com.flower.xin.service.core;

import com.flower.xin.common.dto.UserCouponDto;
import com.flower.xin.common.exception.ServiceException;

import java.util.List;

public interface UserCouponCoreService {
    void createUserCoupon(UserCouponDto dto) throws ServiceException;

    void updateUserCoupon(UserCouponDto dto);

    List<UserCouponDto> getByUid(String uid);

    UserCouponDto getById(Long id);

    void validateUserCopon(Long id);

    void invalidateUserCopon(Long id);

}
