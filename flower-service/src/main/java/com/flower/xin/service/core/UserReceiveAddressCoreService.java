package com.flower.xin.service.core;

import com.flower.xin.common.dto.UserReceiveAddressDto;

public interface UserReceiveAddressCoreService {
    void createAddress(UserReceiveAddressDto dto);

    void updateAddress(UserReceiveAddressDto dto);

    void setDefaultAddress(UserReceiveAddressDto dto);
}
