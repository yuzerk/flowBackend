package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class UserDao implements Serializable {
    private Long id;

    private Long bossId;

    private Long referrerId;

    private String nickName;

    private String userName;

    private String openId;

    private String phoneNum;

    private Integer userType;

    private String city;

    private String avatarUrl;

    private String country;

    private String province;

    private String language;

    private Integer gender;

    private Long defaultReceiveId;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
