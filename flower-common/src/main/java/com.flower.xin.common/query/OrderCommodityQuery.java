package com.flower.xin.common.query;

import lombok.Data;

import java.util.Date;

@Data
public class OrderCommodityQuery {
    private Long id;

    private Long orderId;

    private Long userId;

    private Long shoppingCartId;

    private Integer price;

    private Date createdAt;

    private Date updatedAt;
}
