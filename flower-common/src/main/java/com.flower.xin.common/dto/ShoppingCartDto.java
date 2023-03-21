package com.flower.xin.common.dto;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * @author eiven
 */
@Data
public class ShoppingCartDto {
    private Long id;

    private List<Long> ids;

    private Long userId;

    private Long commodityId;

    private Integer commodityCount;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;
}
