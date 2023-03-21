package com.flower.xin.api.app;

import com.flower.xin.common.dto.UserReceiveAddressDto;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.UserReciveAddressParam;
import com.flower.xin.common.response.LoginResponse;
import com.flower.xin.common.response.UserReceiveAddressResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.UserReceiveAddressCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

/**
 * @author eiven
 */
@Controller
@RequestMapping("/app/receive/address")
public class UserReceiveAddressAppController {

    private final static Logger logger = LoggerFactory.getLogger(UserAppController.class);

    @Autowired
    private UserReceiveAddressCoreService userReceiveAddressCoreService;

    @PostMapping("/create")
    @ResponseBody
    ResponseMessage<UserReceiveAddressResponse> create(@RequestBody UserReciveAddressParam param) throws ServiceException {
        logger.info("添加收货地址requst: {}", JsonUtil.toJson(param));
        validateCreateUserReceiveAddress(param);
        UserReceiveAddressDto dto = buildUserReceiveAddressDto(param);
        userReceiveAddressCoreService.createAddress(dto);
//        UserReceiveAddressResponse response = buildUserReceiveAddressResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @PostMapping("/update")
    @ResponseBody
    ResponseMessage<UserReceiveAddressResponse> update(@RequestBody UserReciveAddressParam param) throws ServiceException {
        logger.info("更新收货地址requst: {}", JsonUtil.toJson(param));
        validateUpdateUserReceiveAddress(param);
        UserReceiveAddressDto dto = buildUserReceiveAddressDto(param);
        userReceiveAddressCoreService.updateAddress(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @PostMapping("/default")
    @ResponseBody
    ResponseMessage<UserReceiveAddressResponse> setDefault(@RequestBody UserReciveAddressParam param) throws ServiceException {
        logger.info("设置默认收货地址requst: {}", JsonUtil.toJson(param));
        validateSetDefaultUserReceiveAddress(param);

        UserReceiveAddressDto dto = buildUserReceiveAddressDto(param);
        userReceiveAddressCoreService.setDefaultAddress(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @PostMapping("/delete")
    @ResponseBody
    ResponseMessage<LoginResponse> delete(@RequestBody UserReciveAddressParam param) throws ServiceException {
        logger.info("删除收货地址requst: {}", JsonUtil.toJson(param));
        validateUpdateUserReceiveAddress(param);
        UserReceiveAddressDto dto = buildUserReceiveAddressDto(param);
        dto.setIsValidate(ValidateEnum.INVALIDATE.getCode());
        userReceiveAddressCoreService.updateAddress(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    private void validateSetDefaultUserReceiveAddress(UserReciveAddressParam param) throws ServiceException {
        if (param == null
                || param.getUid() == null
                || param.getId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private void validateUpdateUserReceiveAddress(UserReciveAddressParam param) throws ServiceException {
        if (param == null
                || param.getUid() == null
                || param.getId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private void validateCreateUserReceiveAddress(UserReciveAddressParam param) throws ServiceException {
        if (param == null
                || StringUtils.isEmpty(param.getCustomerName())
                || StringUtils.isEmpty(param.getCustomerPhone())
                || StringUtils.isEmpty(param.getAddressDetail())
                || StringUtils.isEmpty(param.getProvince())
                || StringUtils.isEmpty(param.getSlCity())
                || StringUtils.isEmpty(param.getStreet())
                || StringUtils.isEmpty(param.getTlCity())
                || param.getUid() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private UserReceiveAddressDto buildUserReceiveAddressDto(UserReciveAddressParam param) {
        UserReceiveAddressDto userReceiveAddressDto = new UserReceiveAddressDto();
        userReceiveAddressDto.setId(param.getId());
        userReceiveAddressDto.setUserId(param.getUid());
        userReceiveAddressDto.setCustomerName(param.getCustomerName());
        userReceiveAddressDto.setCustomerPhone(param.getCustomerPhone());
        userReceiveAddressDto.setProvince(param.getProvince());
        userReceiveAddressDto.setSlCity(param.getSlCity());
        userReceiveAddressDto.setTlCity(param.getTlCity());
        userReceiveAddressDto.setStreet(param.getStreet());
        userReceiveAddressDto.setAddressDetail(param.getAddressDetail());
        userReceiveAddressDto.setIsValidate(param.getIsValidate());
        return userReceiveAddressDto;
    }
}
