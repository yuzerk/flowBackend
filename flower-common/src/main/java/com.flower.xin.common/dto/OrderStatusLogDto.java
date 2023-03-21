package com.flower.xin.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderStatusLogDto {
    private Long id;

    private Long orderId;

    private Short status;

    private Date createdAt;

    private Date updatedAt;
}
