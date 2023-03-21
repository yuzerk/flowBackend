package com.flower.xin.common.enums;

public enum SubjectTypeEnum {
    HEAD_SUBJECT(10, "头部主题"),
    MIDDLE_SUBJECT(20, "中部主题"),
    GENERAL_SUBJECT(30, "普通主题");

    private Integer code;

    private String msg;

    SubjectTypeEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }

    public static String getEnumByCode(Integer code) {
        for(SubjectTypeEnum subjectType : SubjectTypeEnum.values()) {
            if(subjectType.getCode().equals(code)) {
                return subjectType.getMsg();
            }
        }
        return "";
    }
}
