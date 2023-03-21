package com.flower.xin.api.app;

import com.flower.xin.common.dto.CommodityClassifyDto;
import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.dto.CommoditySubjectDto;
import com.flower.xin.common.enums.ClassifyTabSwitchTypeEnum;
import com.flower.xin.common.enums.SubjectTypeEnum;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.CommodityParam;
import com.flower.xin.common.param.CommoditySubjectParam;
import com.flower.xin.common.param.base.RequestParam;
import com.flower.xin.common.param.classifyTab.CommodityTabCheckParam;
import com.flower.xin.common.query.CommodityClassifyQuery;
import com.flower.xin.common.query.CommodityQuery;
import com.flower.xin.common.query.CommoditySubjectQuery;
import com.flower.xin.common.response.CommodityClassifyResponse;
import com.flower.xin.common.response.CommodityResponse;
import com.flower.xin.common.response.CommoditySubjectResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.response.classify.ClassifyAndSubjectResponse;
import com.flower.xin.common.response.classify.ClassifyTabBarResponse;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.CommodityClassifyCoreService;
import com.flower.xin.service.core.CommodityCoreService;
import com.flower.xin.service.core.CommoditySubjectContentCoreService;
import com.flower.xin.service.core.CommoditySubjectCoreService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.thymeleaf.util.StringUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@Controller
@RequestMapping("/app/commodity")
public class CommodityAppController {

    private final static Logger logger = LoggerFactory.getLogger(CommodityAppController.class);

    @Autowired
    private CommodityCoreService commodityCoreService;

    @Autowired
    private CommodityClassifyCoreService commodityClassifyCoreService;

    @Autowired
    private CommoditySubjectCoreService commoditySubjectCoreService;

    @Autowired
    private CommoditySubjectContentCoreService commoditySubjectContentCoreService;

    /**
     * 获取首页滚动栏+居中主题栏
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @GetMapping("/getSubject")
    @ResponseBody
    ResponseMessage<List<CommoditySubjectResponse>> getSubject(CommoditySubjectParam param) throws ServiceException {
        logger.info("查询主题requst: {}", JsonUtil.toJson(param));
        validateGetSubject(param);
        CommoditySubjectQuery query = buildCommoditySubjectQuery(param);
        query.setIsvalidate(ValidateEnum.VALIDATE.getCode());
        List<CommoditySubjectDto> dtos = commoditySubjectCoreService.getCommoditySubject(query);
        if (CollectionUtils.isEmpty(dtos)) {
            return ResultUtil.success(null);
        }
        List<CommoditySubjectResponse> responses = dtos.stream().map(this::buildCommoditySubjectResponse).collect(Collectors.toList());
        logger.info("response: {}", JsonUtil.toJson(responses));
        return ResultUtil.success(responses);
    }

    /**
     * 分页拉取商品
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @GetMapping("/getCommodities")
    @ResponseBody
    ResponseMessage<List<CommodityResponse>> getCommodities(CommodityParam param) throws ServiceException {
        logger.info("查询商品requst: {}", JsonUtil.toJson(param));
        validateGetCommodities(param);
        CommodityQuery query = buildCommodityQuery(param);
        PageInfo<CommodityDto> commodityDtos = commodityCoreService.getCommodities(query);
        if (CollectionUtils.isEmpty((commodityDtos.getList()))) {
            return ResultUtil.success(null);
        }
        logger.info("response: {}", JsonUtil.toJson(commodityDtos));
        return ResultUtil.success(commodityDtos, commodityDtos.getList().stream().map(this::buildCommodityResponse).collect(Collectors.toList()));
    }

    /**
     * 分页拉取商品
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @GetMapping("/getCommoditiesBySubject")
    @ResponseBody
    ResponseMessage<List<CommodityResponse>> getCommoditiesBySubject(CommodityParam param) throws ServiceException {
        logger.info("查询商品requst: {}", JsonUtil.toJson(param));
        validateGetCommoditiesBySubject(param);
        CommodityQuery query = buildCommodityQuery(param);
        query.setIsValidate(ValidateEnum.VALIDATE.getCode());
        List<CommodityDto> commodityDtos = commodityCoreService.getCommoditiesBySubject(query);
        if (CollectionUtils.isEmpty((commodityDtos))) {
            return ResultUtil.success(null);
        }
        List<CommodityResponse> responses = commodityDtos.stream().map(this::buildCommodityResponse).collect(Collectors.toList());
        logger.info("response: {}", JsonUtil.toJson(responses));
        return ResultUtil.success(responses);
    }

    /**
     * 搜索商品
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @RequestMapping("/searchCommodities")
    @ResponseBody
    ResponseMessage<List<CommodityResponse>> searchCommodities(CommodityParam param) throws ServiceException {
        logger.info("搜索商品requst: {}", JsonUtil.toJson(param));
        validateSearchCommodities(param);
        CommodityQuery query = buildCommodityQuery(param);
        PageInfo<CommodityDto> pageInfo = commodityCoreService.searchCommodities(query);
        if (CollectionUtils.isEmpty((pageInfo.getList()))) {
            return ResultUtil.success(null);
        }
        logger.info("response: {}", JsonUtil.toJson(pageInfo));
        return ResultUtil.success(pageInfo);
    }

    /**
     * 分类tabbar页面初始化所需要的接口
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @RequestMapping("/classifyTabBar")
    @ResponseBody
    ResponseMessage<ClassifyTabBarResponse> classifyTabBar(RequestParam param) throws ServiceException {

        List<CommodityClassifyDto> commodityClassify = commodityClassifyCoreService.getCommodityClassify(new CommodityClassifyQuery());
        CommoditySubjectQuery querySub = new CommoditySubjectQuery();
        querySub.setType(SubjectTypeEnum.GENERAL_SUBJECT.name());
        List<CommoditySubjectDto> commoditySubjectDtos = commoditySubjectCoreService.getCommoditySubject(querySub);

        CommodityQuery query = new CommodityQuery();
        query.setClassifyId(commodityClassify.size() == 0? null : commodityClassify.get(0).getId());
        // 不分页
        query.setLimit(0);
        query.setPage(0);
        PageInfo<CommodityDto> pageInfo = commodityCoreService.getCommodities(query);
        ClassifyTabBarResponse response = new ClassifyTabBarResponse();
        response.setCommodityDtos(pageInfo.getList().stream().map(this::buildCommodityResponse).collect(Collectors.toList()));
        response.setClassifyAndSubjectResponses(buildClassifyAndSubjectResponse(commodityClassify, commoditySubjectDtos));
        return ResultUtil.success(response);
    }

    /**
     * 分类页面 切换tab页
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @GetMapping("/classifyTabChange")
    @ResponseBody
    ResponseMessage<List<CommodityResponse>> classifyTabChange(CommodityTabCheckParam param) throws ServiceException {
        CommodityQuery query = new CommodityQuery();
        query.setPage(0);
        query.setLimit(0);
        if (ClassifyTabSwitchTypeEnum.CLASSIFY_SWITCH_TYPE.getCode().equals(param.getType())) {
            query.setClassifyId(param.getId());
        }else if (ClassifyTabSwitchTypeEnum.SUBJECT_SWITCH_TYPE.getCode().equals(param.getType())) {
            query.setSubjectId(param.getId());
        }
        PageInfo<CommodityDto> pageInfo = commodityCoreService.getCommodities(query);
        return ResultUtil.success(pageInfo.getList().stream().map(this::buildCommodityResponse).collect(Collectors.toList()));
    }

    private CommodityResponse buildCommodityResponse(CommodityDto dto) {
        CommodityResponse commodityResponse = new CommodityResponse();
        commodityResponse.setId(dto.getId());
        commodityResponse.setName(dto.getName());
        commodityResponse.setCommodityDesc(dto.getCommodityDesc());
        commodityResponse.setDetail(dto.getDetail());
        commodityResponse.setPicList(JsonUtil.fromJson(dto.getPicList(), List.class));
        commodityResponse.setDetailsPicList(dto.getDetailsPicList());
        commodityResponse.setOriginalPrice(dto.getOriginalPrice());
        commodityResponse.setCurrentPrice(dto.getCurrentPrice());
        commodityResponse.setClassifyId(dto.getClassifyId());
        commodityResponse.setCommodityType(dto.getCommodityType());
        commodityResponse.setBossId(dto.getBossId());
        commodityResponse.setPayload(dto.getPayload());
        commodityResponse.setFirstPic(dto.getFirstPic());
        return commodityResponse;
    }

    private CommodityQuery buildCommodityQuery(CommodityParam param) {
        CommodityQuery commodityQuery = new CommodityQuery();
        commodityQuery.setId(param.getId());
        commodityQuery.setLimit(param.getLimit());
        if (param.getPage() != null) {
            commodityQuery.setPage(param.getPage() - 1);
        }
        commodityQuery.setNameLike(param.getNameLike());
        commodityQuery.setClassifyId(param.getClassifyId());
        commodityQuery.setSubjectId(param.getSubjectId());
        return commodityQuery;
    }

    private void validateSearchCommodities(CommodityParam param) throws ServiceException {
        if (param == null
                || StringUtils.isEmpty(param.getNameLike())
                || param.getLimit() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private void validateGetCommoditiesBySubject(CommodityParam param) throws ServiceException {
        if (param == null
                || param.getSubjectId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private void validateGetCommodities(CommodityParam param) throws ServiceException {
        if (param == null
                || param.getLimit() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private void validateGetSubject(CommoditySubjectParam param) throws ServiceException {
        if (param == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }


    private CommoditySubjectResponse buildCommoditySubjectResponse(CommoditySubjectDto commoditySubjectDto) {
        CommoditySubjectResponse commoditySubjectResponse = new CommoditySubjectResponse();
        commoditySubjectResponse.setId(commoditySubjectDto.getId());
        commoditySubjectResponse.setName(commoditySubjectDto.getName());
        commoditySubjectResponse.setType(commoditySubjectDto.getType());
        commoditySubjectResponse.setScore(commoditySubjectDto.getScore());
        commoditySubjectResponse.setPicList(JsonUtil.fromJson(commoditySubjectDto.getPicList(), List.class));
        return commoditySubjectResponse;
    }

    private CommoditySubjectQuery buildCommoditySubjectQuery(CommoditySubjectParam param) {
        CommoditySubjectQuery commoditySubjectQuery = new CommoditySubjectQuery();
        commoditySubjectQuery.setId(param.getId());
        commoditySubjectQuery.setName(param.getName());
        commoditySubjectQuery.setType(param.getType());
        commoditySubjectQuery.setIsvalidate(param.getIsValidate());
        return commoditySubjectQuery;
    }

    private CommodityClassifyResponse buildCommodityClassify(CommodityClassifyDto dto) {
        CommodityClassifyResponse response = new CommodityClassifyResponse();
        BeanUtils.copyProperties(dto, response);
        return response;
    }

    private List<ClassifyAndSubjectResponse> buildClassifyAndSubjectResponse(List<CommodityClassifyDto> classifyDtos,
                                                                       List<CommoditySubjectDto> subjectDtos) {
        List<ClassifyAndSubjectResponse> res = new ArrayList<>();
        List<ClassifyAndSubjectResponse> classify = classifyDtos.stream().map(ClassifyAndSubjectResponse::new).collect(Collectors.toList());
        List<ClassifyAndSubjectResponse> subject = subjectDtos.stream().map(ClassifyAndSubjectResponse::new).collect(Collectors.toList());
        res.addAll(classify);
        res.addAll(subject);
        return res;
    }
}
