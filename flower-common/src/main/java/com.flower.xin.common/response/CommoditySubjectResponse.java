package com.flower.xin.common.response;

import com.flower.xin.common.response.base.Response;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectResponse {
    private Long id;

    private String name;

    private Integer type;

    private String typeMsg;

    private Integer score;

    private List<String> picList;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;
}
