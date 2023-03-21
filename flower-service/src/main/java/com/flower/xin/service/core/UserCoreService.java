package com.flower.xin.service.core;

import com.flower.xin.common.dto.LoginDto;
import com.flower.xin.common.dto.UserDto;

public interface UserCoreService {
    void loginAndRegister(LoginDto dto);

    void login(LoginDto dto);

    void checkToken(LoginDto dto);

    UserDto getUser(Long userId);
}
