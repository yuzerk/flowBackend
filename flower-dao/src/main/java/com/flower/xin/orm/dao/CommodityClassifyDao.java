package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eiven
 */
@Data
public class CommodityClassifyDao implements Serializable {
    private Long id;

    private String name;

    private Integer score;

    private Boolean scoreSortDesc;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
