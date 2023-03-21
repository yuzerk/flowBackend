package com.flower.xin.orm.dao;

import com.flower.xin.orm.dao.base.BaseDao;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
public class CommodityDao extends BaseDao implements Serializable {
    private Long id;

    private List<Long> ids;

    private String name;

    private String commodityDesc;

    private String detail;

    private String picList;

    private String detailsPicList;

    private Integer originalPrice;

    private Integer currentPrice;

    private Long classifyId;

    private Integer commodityType;

    private Long bossId;

    private String payload;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
