package com.flower.xin.common.response.classify;

import com.flower.xin.common.dto.CommodityClassifyDto;
import com.flower.xin.common.dto.CommoditySubjectDto;
import com.flower.xin.common.enums.ClassifyTabSwitchTypeEnum;
import lombok.Data;
import org.springframework.beans.BeanUtils;

@Data
public class ClassifyAndSubjectResponse {

    private Long id;

    private String name;

    // 1-分类classify 2-主题 subject
    private Integer type;

    public ClassifyAndSubjectResponse(CommodityClassifyDto classifyDto) {
        BeanUtils.copyProperties(classifyDto, this);
        this.setType(ClassifyTabSwitchTypeEnum.CLASSIFY_SWITCH_TYPE.getCode());
    }

    public ClassifyAndSubjectResponse(CommoditySubjectDto subjectDto) {
        BeanUtils.copyProperties(subjectDto, this);
        this.setType(ClassifyTabSwitchTypeEnum.SUBJECT_SWITCH_TYPE.getCode());
    }
}
