package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectContentDao implements Serializable {
    private Long id;

    private List<Long> commoditySubjectIds;

    private Long commoditySubjectId;

    private Long commodityId;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
