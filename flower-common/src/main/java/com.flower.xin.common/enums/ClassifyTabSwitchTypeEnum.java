package com.flower.xin.common.enums;

public enum  ClassifyTabSwitchTypeEnum {
    CLASSIFY_SWITCH_TYPE(1,  "选的是分类"),
    SUBJECT_SWITCH_TYPE(2,  "选的是主题"),
    ;

    ClassifyTabSwitchTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    private Integer code;

    private String msg;

    public Integer getCode() {
        return code;
    }

    public void setCode(Integer code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
