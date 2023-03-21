package com.flower.xin.api.app;

import com.flower.xin.common.dto.UserCouponDto;
import com.flower.xin.common.enums.CouponTypeEnum;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.UserCouponParam;
import com.flower.xin.common.response.UserCouponRespons;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.UserCouponCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@Controller
@RequestMapping("/app/userCoupon")
public class UserCouponAppController {
    private final static Logger logger = LoggerFactory.getLogger(UserAppController.class);

    @Autowired
    private UserCouponCoreService userCouponCoreService;

    @Autowired
    private HttpServletRequest request;

    @RequestMapping("create")
    @ResponseBody
    ResponseMessage<UserCouponRespons> create(@RequestBody UserCouponParam param) throws ServiceException {
        logger.info("领取优惠券requst: {}", JsonUtil.toJson(param));
        validateCreate(param);
        UserCouponDto dto = buildUserCoupon(param);
        userCouponCoreService.createUserCoupon(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @RequestMapping("use")
    @ResponseBody
    ResponseMessage<UserCouponRespons> use(@RequestBody UserCouponParam param) throws ServiceException {
        logger.info("使用优惠券requst: {}", JsonUtil.toJson(param));
        validateUse(param);
        UserCouponDto dto = new UserCouponDto();
        dto.setId(param.getId());
        dto.setIsValidate(ValidateEnum.INVALIDATE.getCode());
        userCouponCoreService.updateUserCoupon(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @RequestMapping("getByUid")
    @ResponseBody
    ResponseMessage<UserCouponRespons> use() throws ServiceException {
        String uid = request.getHeader("uid");
        if (StringUtils.isEmpty(uid)) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
        List<UserCouponRespons> respons = userCouponCoreService.getByUid(uid).stream()
                .map(this::buildUserCouponRespons).collect(Collectors.toList());
        return ResultUtil.success(respons);
    }

    private void validateUse(UserCouponParam param) throws ServiceException {
        if (param == null
                || param.getUid() == null
                || param.getId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private UserCouponDto buildUserCoupon(UserCouponParam param) {
        UserCouponDto userCouponDto = new UserCouponDto();
        userCouponDto.setId(param.getId());
        userCouponDto.setUserId(param.getUid());
        userCouponDto.setCouponCategoryId(param.getCouponCategoryId());
        return userCouponDto;
    }

    private void validateCreate(UserCouponParam param) throws ServiceException {
        if (param == null
                || param.getUid() == null
                || param.getCouponCategoryId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private UserCouponRespons buildUserCouponRespons(UserCouponDto dto) {
        UserCouponRespons res = new UserCouponRespons();
        BeanUtils.copyProperties(dto, res);
        res.setCouponTypeDesc(CouponTypeEnum.getMsg(dto.getCouponType()));
        return  res;
    }
}
