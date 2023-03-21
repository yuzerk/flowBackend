package com.flower.xin.common.enums;

public enum ShippingOrderStatusEnum {
    CREATE(0, "创建运单"),
    WAIT_DISPATCH(10, "待配送"),
    DISPATCHING(20, "配送中"),
    FAIL(80, "失败"),
    COMPLETE(90, "完成");

    private Integer code;

    private String msg;

    ShippingOrderStatusEnum(Integer code, String msg) {
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
