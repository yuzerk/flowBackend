package com.flower.xin.common.query;

import lombok.Data;

import java.util.List;

@Data
public class CommodityQuery extends Query{
    private Long id;

    private List<Long> ids;

    private String nameLike;

    private Long classifyId;

    private Long subjectId;

    private Integer isValidate;
}
