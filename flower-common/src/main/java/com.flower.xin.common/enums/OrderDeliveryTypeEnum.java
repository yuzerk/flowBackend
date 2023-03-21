package com.flower.xin.common.enums;

public enum  OrderDeliveryTypeEnum {
    SELF(10, "自提"),
    OUTSIDE(20, "外送");

    private Integer code;
    private String msg;

    OrderDeliveryTypeEnum(Integer code, String msg) {
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
