package com.flower.xin.common.exception;

import org.springframework.util.StringUtils;

public enum ErrorMessageEnum {
    SUCCESS("0", "SUCCESS"),

    AUTH_TOKEN_ERROR("401", "用户信息校验失败"),
    USER_TOKEN_ERROR("410", "登陆过期"),
    USER_TOKEN_MISS_ERROR("411", "登陆过期"),
    USER_NOT_REGISTER_ERROR("412", "未注册用户"),
    USER_TOKEN_EXPIRE_ERROR("413", "登陆过期"),
    USER_TOKEN_ENCODE_ERROR("414", "登陆过期"),
    MISSING_PARAM_ERROR("415", "缺少必要参数"),
    HAVE_NO_COUPON_TYPE("416", "优惠券已发完"),
    MISSING_USER_INFO_ERROR("417", "缺少用户信息"),
    ORDER_CAN_NOT_CANCEL_ERROR("418", "此订单已支付，不允许取消"),
    ORDER_CAN_NOT_PAY_ERROR("419", "此订单状态不允许支付"),
    ORDER_PAY_EXPIRE_ERROR("420", "此订单已过期，请重新创建订单"),


    SYSTEM_ERROR("501", "系统繁忙");

    private final String code;
    private final String msg;

    public static ErrorMessageEnum getByCode(String code) {
        if (!StringUtils.isEmpty(code)) {
            for (ErrorMessageEnum e : values()) {
                if (code.equals(e.getCode())) {
                    return e;
                }
            }
        }
        return null;
    }

    ErrorMessageEnum(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public ServiceException getServiceException() {
        return new ServiceException(code, msg);
    }

    public SystemException getSystemException() {
        return new SystemException(code, msg);
    }
}
