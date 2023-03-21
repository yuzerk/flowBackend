package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderCommodityDao implements Serializable {
    private Long id;

    private Long orderId;

    private Long userId;

    private Long shoppingCartId;

    private Integer price;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;


}
