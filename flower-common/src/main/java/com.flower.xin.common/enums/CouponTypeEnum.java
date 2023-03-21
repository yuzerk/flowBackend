package com.flower.xin.common.enums;

/**
 * @author eiven
 */
public enum CouponTypeEnum {
    SHOPPING_COUPON(10, "商品优惠券"),
    DISCOUNT_COUPON(20, "折扣优惠券");

    private Integer code;

    private String msg;

    private CouponTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getMsg(Integer code) {
        for (CouponTypeEnum value : CouponTypeEnum.values()) {
            if (value.code.equals(code)) {
                return value.msg;
            }
        }
        return "";
    }
}
