package com.flower.xin.api.admin;

import com.flower.xin.common.dto.LoginDto;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.LoginParam;
import com.flower.xin.common.response.LoginResponse;
import com.flower.xin.common.response.UserResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.UserCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;


/**
 * @author eiven
 */
@CrossOrigin
@Controller
@RequestMapping("/admin/user")
public class UserAdminController {

    private final static Logger logger = LoggerFactory.getLogger(UserAdminController.class);

    @Autowired
    private UserCoreService userCoreService;

    @PostMapping("login")
    @ResponseBody
    ResponseMessage<LoginResponse> login(LoginParam param) throws ServiceException {
//        logger.info("登陆requst: {}", JsonUtil.toJson(param));
//        validateLoginAndRegister(param);
//        LoginDto dto = buildLoginDto(param);
//        userCoreService.loginAndRegister(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("登陆response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @RequestMapping("/user")
    @ResponseBody
    public ResponseEntity<UserResponse> user(){
        UserResponse response = new UserResponse();
        response.setName("FlowerFather");
        response.setRoles("[\"admin\"]");
        response.setTel("123");
        response.setPosition("花店");
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    private void validateLoginAndRegister(LoginParam param) throws ServiceException {
        if(param == null
        || StringUtils.isEmpty(param.getCode())
        || param.getBossId() == null
        || StringUtils.isEmpty(param.getNickName())) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private LoginResponse buildLoginResponse(LoginDto dto) {
        LoginResponse loginResponse = new LoginResponse();
        loginResponse.setUid(dto.getUid());
        loginResponse.setAppToken(dto.getAppToken());
        return loginResponse;
    }

    private LoginDto buildLoginDto(LoginParam param) {
        LoginDto loginDto = new LoginDto();
        loginDto.setCode(param.getCode());
        loginDto.setAvatarUrl(param.getAvatarUrl());
        loginDto.setCity(param.getCity());
        loginDto.setCountry(param.getCountry());
        loginDto.setGender(param.getGender());
        loginDto.setLanguage(param.getLanguage());
        loginDto.setNickName(param.getNickName());
        loginDto.setProvince(param.getProvince());
        loginDto.setBossId(param.getBossId());
        return loginDto;
    }
}
