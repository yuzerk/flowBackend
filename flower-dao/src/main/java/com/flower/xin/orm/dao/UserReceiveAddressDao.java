package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserReceiveAddressDao implements Serializable {
    private Long id;

    private Long userId;

    private String customerName;

    private String customerPhone;

    private String province;

    private String slCity;

    private String tlCity;

    private String street;

    private String addressDetail;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
