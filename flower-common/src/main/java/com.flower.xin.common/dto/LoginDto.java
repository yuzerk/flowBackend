package com.flower.xin.common.dto;

import lombok.Data;

@Data
public class LoginDto {
    /* 微信小程序传输字段*/
    /**
     * 小程序登陆校验code
     */
    private String code;

    private String avatarUrl;

    private String city;

    private String country;

    private Integer gender;

    private String language;

    private String nickName;

    private String province;

    private Long bossId;

    /* 回传字段 */
    private String appToken;

    private Long uid;
}
