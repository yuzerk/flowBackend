package com.flower.xin.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CouponCategoryDto {
    private Long id;

    private Float discounts;

    private Integer couponType;

    private Long expireTime;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;

}
