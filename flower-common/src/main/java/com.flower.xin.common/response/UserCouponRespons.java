package com.flower.xin.common.response;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.flower.xin.common.response.base.Response;
import lombok.Data;

import java.util.Date;

@Data
public class UserCouponRespons {
    private Long id;

    private Long couponCategoryId;

    private Float discounts;

    private Integer couponType;

    private Integer isValidate;

    @JsonFormat(pattern="yyyy-MM-dd HH:mm:ss",timezone="GMT+8")
    private Date expireAt;

    private String couponTypeDesc;
}
