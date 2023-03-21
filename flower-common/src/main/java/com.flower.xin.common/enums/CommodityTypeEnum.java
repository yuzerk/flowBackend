package com.flower.xin.common.enums;

public enum CommodityTypeEnum {
    GENERAL_COMMODITY(10, "普通商品"),
    COUPON_COMMODITY(20, "优惠券商品");

    private Integer code;

    private String msg;

    private CommodityTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
