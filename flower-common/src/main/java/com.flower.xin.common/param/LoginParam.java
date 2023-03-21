package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

@Data
public class LoginParam extends RequestParam {
    private String code;

    private String avatarUrl;

    private String city;

    private String country;

    private Integer gender;

    private String language;

    private String nickName;

    private String province;

    private Long bossId;
}
