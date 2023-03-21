package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserCouponDao implements Serializable {
    private Long id;

    private Long userId;

    private Long couponCategoryId;

    private Integer discounts;

    private Integer couponType;

    private Integer isValidate;

    private Date expireAt;

    private Date createdAt;

    private Date updatedAt;
}
