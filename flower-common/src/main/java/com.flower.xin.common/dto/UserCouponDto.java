package com.flower.xin.common.dto;

import lombok.Data;

import java.util.Date;

@Data
public class UserCouponDto {
    private Long id;

    private Long userId;

    private Long couponCategoryId;

    private Float discounts;

    private Integer couponType;

    private Integer isValidate;

    private Long expireTime;

    private Date expireAt;

    private Date createdAt;

    private Date updatedAt;
}
