package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CouponCategoryDao implements Serializable {
    private Long id;

    private Integer discounts;

    private Integer couponType;

    private Long expireTime;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
