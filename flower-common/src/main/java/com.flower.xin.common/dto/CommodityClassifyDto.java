package com.flower.xin.common.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author eiven
 */
@Data
public class CommodityClassifyDto {
    private Long id;

    private String name;

    private Integer score;

    private Date createdAt;

    private Date updatedAt;
}
