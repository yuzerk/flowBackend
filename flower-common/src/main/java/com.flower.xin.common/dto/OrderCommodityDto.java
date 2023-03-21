package com.flower.xin.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderCommodityDto {
    private Long id;

    private Long orderId;

    private Long userId;

    private Long shoppingCartId;

    private Float price;

    private Date createdAt;

    private Date updatedAt;
}
