package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class CommoditySubjectDao implements Serializable {
    private Long id;

    private String name;

    private Integer type;

    private Integer score;

    private String picList;

    private Boolean scoreSortDesc;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
