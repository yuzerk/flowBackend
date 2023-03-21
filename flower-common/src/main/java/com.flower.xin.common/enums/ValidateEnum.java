package com.flower.xin.common.enums;

public enum ValidateEnum {
    INVALIDATE(0, "无效"),
    VALIDATE(1,"有效");

    private Integer code;

    private String msg;

    private ValidateEnum(Integer code, String msg) {
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
