package com.flower.xin.api.admin;

import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.enums.CommodityTypeEnum;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.CommodityParam;
import com.flower.xin.common.query.CommodityQuery;
import com.flower.xin.common.response.CommodityResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.CommodityCoreService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@CrossOrigin
@Controller
@RequestMapping("/admin/commodity")
public class CommodityAdminController {
    private final static Logger logger = LoggerFactory.getLogger(CommodityAdminController.class);

    @Autowired
    private CommodityCoreService commodityCoreService;

    @PostMapping("/create")
    @ResponseBody
    ResponseMessage<CommodityResponse> create(@RequestBody CommodityParam param) throws ServiceException {
        logger.info("创建商品requst: {}", JsonUtil.toJson(param));
        validateCreate(param);
        CommodityDto commodityDto = buildCommodityDto(param);
        commodityDto.setIsValidate(ValidateEnum.VALIDATE.getCode());
        commodityCoreService.createCommodities(buildCommodityDto(param));
//        ShoppingCartDto dto = buildShoppingCartDto(param);
//        shoppingCartCoreService.createShoppingCart(dto);
////        LoginResponse response = buildLoginResponse(dto);
////        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }


    /**
     * 商品价格修改 || 商品信息修改 || 商品上下架
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @RequestMapping("/update")
    @ResponseBody
    ResponseMessage<CommodityResponse> update(@RequestBody CommodityParam param) throws ServiceException {
        logger.info("商品信息修改requst: {}", JsonUtil.toJson(param));
        validateUpdate(param);
        commodityCoreService.updateCommodity(buildCommodityDto(param));
//        ShoppingCartDto dto = buildShoppingCartDto(param);
//        shoppingCartCoreService.createShoppingCart(dto);
////        LoginResponse response = buildLoginResponse(dto);
////        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    private void validateUpdate(CommodityParam param) throws ServiceException {
        if (param == null
                || param.getId() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    /**
     * 商品搜索
     *
     * @param param
     * @return
     * @throws ServiceException
     */
    @PostMapping("/get")
    @ResponseBody
    ResponseMessage<List<CommodityResponse>> getCommodities(@RequestBody CommodityParam param) throws ServiceException {
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
        commodityResponse.setIsValidate(dto.getIsValidate());
        return commodityResponse;
    }

    private CommodityDto buildCommodityDto(CommodityParam param) {
        CommodityDto commodityDto = new CommodityDto();
        commodityDto.setId(param.getId());
        commodityDto.setIds(param.getIds());
        commodityDto.setName(param.getName());
        commodityDto.setCommodityDesc(param.getCommodityDesc());
        commodityDto.setDetail(param.getDetail());
        if (!CollectionUtils.isEmpty(param.getPicList())) {
            commodityDto.setPicList(JsonUtil.toJson(param.getPicList()));
        }
        commodityDto.setDetailsPicList(param.getDetailsPicList());
        commodityDto.setOriginalPrice(param.getOriginalPrice());
        commodityDto.setCurrentPrice(param.getCurrentPrice());
        commodityDto.setClassifyId(param.getClassifyId());
        commodityDto.setSubjectId(param.getSubjectId());
        if(param.getCommodityType() != null) {
            commodityDto.setCommodityType(CommodityTypeEnum.valueOf(param.getCommodityType()).getCode());
        }
        commodityDto.setBossId(param.getBossId());
        commodityDto.setPayload(param.getPayload());
        commodityDto.setIsValidate(param.getIsValidate());
        commodityDto.setNameLike(param.getNameLike());
        return commodityDto;
    }

    private void validateCreate(CommodityParam param) throws ServiceException {
        if (param == null
                || param.getName() == null
                || param.getBossId() == null
                || param.getClassifyId() == null
                || param.getCommodityDesc() == null
                || param.getCommodityType() == null
                || param.getCurrentPrice() == null
                || param.getDetail() == null
                || param.getOriginalPrice() == null
                || param.getPicList() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private void validateGetCommodities(CommodityParam param) throws ServiceException {
        if (param == null
                || param.getLimit() == null
                || param.getPage() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private CommodityQuery buildCommodityQuery(CommodityParam param) {
        CommodityQuery commodityQuery = new CommodityQuery();
        commodityQuery.setId(param.getId());
        commodityQuery.setLimit(param.getLimit());
        if (param.getPage() != null) {
            commodityQuery.setPage(param.getPage() - 1);
        }
        commodityQuery.setClassifyId(param.getClassifyId());
        return commodityQuery;
    }
}
