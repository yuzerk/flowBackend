package com.flower.xin.api.admin;

import com.flower.xin.common.dto.CommoditySubjectDto;
import com.flower.xin.common.enums.SubjectTypeEnum;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.CommoditySubjectParam;
import com.flower.xin.common.query.CommoditySubjectQuery;
import com.flower.xin.common.response.CommoditySubjectResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.CommoditySubjectCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@CrossOrigin
@Controller
@RequestMapping("/admin/commodity/subject")
public class CommoditySubjectAdminController {
    private final static Logger logger = LoggerFactory.getLogger(CommoditySubjectAdminController.class);

    @Autowired
    private CommoditySubjectCoreService commoditySubjectCoreService;

    @RequestMapping("/create")
    @ResponseBody
    ResponseMessage<CommoditySubjectResponse> create(@RequestBody CommoditySubjectParam param) throws ServiceException {
        logger.info("添加主题requst: {}", JsonUtil.toJson(param));
        validateCreate(param);
        param.setIsValidate(ValidateEnum.VALIDATE.getCode());
        CommoditySubjectDto subjectDto = buildCommoditySubjectDto(param);
        commoditySubjectCoreService.createCommoditySubject(subjectDto);
        return ResultUtil.success(null);
    }

    /**
     * 名称修改 || 上下架
     * @param param
     * @return
     * @throws ServiceException
     */
    @RequestMapping("/update")
    @ResponseBody
    ResponseMessage<CommoditySubjectResponse> update(@RequestBody CommoditySubjectParam param) throws ServiceException {
        logger.info("修改主题requst: {}", JsonUtil.toJson(param));
        validateUpdate(param);
        CommoditySubjectDto subjectDto = buildCommoditySubjectDto(param);
        commoditySubjectCoreService.updateCommoditySubject(subjectDto);
        return ResultUtil.success(null);
    }

    @PostMapping("/get")
    @ResponseBody
    ResponseMessage<List<CommoditySubjectResponse>> get(@RequestBody CommoditySubjectParam param) throws ServiceException {
        logger.info("搜索商品主题requst: {}", JsonUtil.toJson(param));
        List<CommoditySubjectResponse> responses = commoditySubjectCoreService.getCommoditySubject(new CommoditySubjectQuery())
                .stream()
                .map(this::buildCommoditySubjectResponse)
                .collect(Collectors.toList());
        logger.info("搜索商品主题response: {}", JsonUtil.toJson(responses));
        return ResultUtil.success(responses);
    }

    private CommoditySubjectResponse buildCommoditySubjectResponse(CommoditySubjectDto dto) {
        CommoditySubjectResponse commoditySubjectResponse = new CommoditySubjectResponse();
        commoditySubjectResponse.setId(dto.getId());
        commoditySubjectResponse.setName(dto.getName());
        commoditySubjectResponse.setType(dto.getType());
        commoditySubjectResponse.setTypeMsg(SubjectTypeEnum.getEnumByCode(dto.getType()));
        commoditySubjectResponse.setScore(dto.getScore());
        commoditySubjectResponse.setPicList(JsonUtil.fromJson(dto.getPicList(), List.class));
        commoditySubjectResponse.setIsValidate(dto.getIsValidate());
        commoditySubjectResponse.setCreatedAt(dto.getCreatedAt());
        commoditySubjectResponse.setUpdatedAt(dto.getUpdatedAt());
        return commoditySubjectResponse;
    }

    private void validateUpdate(CommoditySubjectParam param) throws ServiceException {
        if (param == null
                || param.getId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private CommoditySubjectDto buildCommoditySubjectDto(CommoditySubjectParam param) {
        CommoditySubjectDto commoditySubjectDto = new CommoditySubjectDto();
        commoditySubjectDto.setId(param.getId());
        commoditySubjectDto.setName(param.getName());
        if(param.getType() != null) {
            commoditySubjectDto.setType(SubjectTypeEnum.valueOf(param.getType()).getCode());
        }
        commoditySubjectDto.setScore(param.getScore());
        if(param.getPicList() != null) {
            commoditySubjectDto.setPicList(JsonUtil.toJson(param.getPicList()));
        }
        commoditySubjectDto.setIsValidate(param.getIsValidate());
        return commoditySubjectDto;
    }

    private void validateCreate(CommoditySubjectParam param) throws ServiceException {
        if (param == null
                || param.getType() == null
                || param.getName() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }
}
