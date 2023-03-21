package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class CommoditySubjectParam extends RequestParam {
    private Long id;

    /**
     * SubjectTypeEnum
     */
    private String type;

    private String name;

    private Integer score;

    private List<String> picList;

    private Integer isValidate;
}
