package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eiven
 */
@Data
public class UserTokenDao implements Serializable {
    private Long id;

    private Long userId;

    private String token;

    private Date expireAt;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
