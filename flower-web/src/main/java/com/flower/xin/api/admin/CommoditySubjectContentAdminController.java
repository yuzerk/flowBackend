package com.flower.xin.api.admin;

import com.flower.xin.common.dto.CommoditySubjectContentDto;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.CommoditySubjectContentParam;
import com.flower.xin.common.query.CommoditySubjectContentQuery;
import com.flower.xin.common.response.CommoditySubjectContentResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.CommoditySubjectContentCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@CrossOrigin
@Controller
@RequestMapping("/admin/commodity/subject/content")
public class CommoditySubjectContentAdminController {
    private final static Logger logger = LoggerFactory.getLogger(CommoditySubjectContentAdminController.class);

    @Autowired
    private CommoditySubjectContentCoreService commoditySubjectContentCoreService;

    @RequestMapping("/create")
    @ResponseBody
    ResponseMessage<CommoditySubjectContentResponse> create(@RequestBody CommoditySubjectContentParam param) throws ServiceException {
//        logger.info("加入购物车requst: {}", JsonUtil.toJson(param));
//        validateCreate(param);
//        ShoppingCartDto dto = buildShoppingCartDto(param);
//        shoppingCartCoreService.createShoppingCart(dto);
////        LoginResponse response = buildLoginResponse(dto);
////        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @RequestMapping("/update")
    @ResponseBody
    ResponseMessage<CommoditySubjectContentResponse> update(@RequestBody CommoditySubjectContentParam param) throws ServiceException {
        logger.info("更新主题商品requst: {}", JsonUtil.toJson(param));
        validateUpdate(param);
        CommoditySubjectContentDto dto = buildCommoditySubjectContentDto(param);
        commoditySubjectContentCoreService.updateCommoditySubject(dto);
        return ResultUtil.success(null);
    }

    private CommoditySubjectContentDto buildCommoditySubjectContentDto(CommoditySubjectContentParam param) {
        CommoditySubjectContentDto commoditySubjectContentDto = new CommoditySubjectContentDto();
        commoditySubjectContentDto.setId(param.getId());
        commoditySubjectContentDto.setCommoditySubjectId(param.getCommoditySubjectId());
        commoditySubjectContentDto.setCommoditySubjectIds(param.getCommoditySubjectIds());
        commoditySubjectContentDto.setCommodityId(param.getCommodityId());
        commoditySubjectContentDto.setCreatedAt(param.getCreatedAt());
        commoditySubjectContentDto.setUpdatedAt(param.getUpdatedAt());
        return commoditySubjectContentDto;
    }

    private void validateUpdate(CommoditySubjectContentParam param) throws ServiceException {
        if (param == null
                || param.getCommodityId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    @RequestMapping("/get")
    @ResponseBody
    ResponseMessage<List<CommoditySubjectContentResponse>> get(@RequestBody CommoditySubjectContentParam param) throws ServiceException {
        logger.info("查询主题商品requst: {}", JsonUtil.toJson(param));
        validateGet(param);
        CommoditySubjectContentQuery query = buildCommoditySubjectContentQuery(param);
        List<CommoditySubjectContentDto> commoditySubjects = commoditySubjectContentCoreService.getCommoditySubject(query);
        if (CollectionUtils.isEmpty((commoditySubjects))) {
            return ResultUtil.success(null);
        }
        List<CommoditySubjectContentResponse> responses = commoditySubjects.stream().map(this::buildCommoditySubjectContentResponse).collect(Collectors.toList());
        logger.info("response: {}", JsonUtil.toJson(responses));
        return ResultUtil.success(responses);
    }

    private CommoditySubjectContentResponse buildCommoditySubjectContentResponse(CommoditySubjectContentDto commoditySubjectContentDto) {
        CommoditySubjectContentResponse commoditySubjectContentResponse = new CommoditySubjectContentResponse();
        commoditySubjectContentResponse.setId(commoditySubjectContentDto.getId());
        commoditySubjectContentResponse.setCommoditySubjectId(commoditySubjectContentDto.getCommoditySubjectId());
        commoditySubjectContentResponse.setCommoditySubjectIds(commoditySubjectContentDto.getCommoditySubjectIds());
        commoditySubjectContentResponse.setCommodityId(commoditySubjectContentDto.getCommodityId());
        commoditySubjectContentResponse.setCreatedAt(commoditySubjectContentDto.getCreatedAt());
        commoditySubjectContentResponse.setUpdatedAt(commoditySubjectContentDto.getUpdatedAt());
        return commoditySubjectContentResponse;
    }

    private CommoditySubjectContentQuery buildCommoditySubjectContentQuery(CommoditySubjectContentParam param) {
        CommoditySubjectContentQuery commoditySubjectContentQuery = new CommoditySubjectContentQuery();
        commoditySubjectContentQuery.setId(param.getId());
        commoditySubjectContentQuery.setCommoditySubjectId(param.getCommoditySubjectId());
        commoditySubjectContentQuery.setCommoditySubjectIds(param.getCommoditySubjectIds());
        commoditySubjectContentQuery.setCommodityId(param.getCommodityId());
        return commoditySubjectContentQuery;
    }

    private void validateGet(CommoditySubjectContentParam param) throws ServiceException {
        if (param == null
                || param.getCommodityId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }
}
