package com.flower.xin.api.admin;

import com.flower.xin.common.dto.CommodityClassifyDto;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.CommodityClassifyParam;
import com.flower.xin.common.query.CommodityClassifyQuery;
import com.flower.xin.common.response.CommodityClassifyResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.CommodityClassifyCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author eiven
 */
@CrossOrigin
@Controller
@RequestMapping("/admin/commodity/classify")
public class CommodityClassifyAdminController {

    private final static Logger logger = LoggerFactory.getLogger(CommodityClassifyAdminController.class);

    @Autowired
    private CommodityClassifyCoreService commodityClassifyCoreService;

    @PostMapping("/create")
    @ResponseBody
    ResponseMessage<CommodityClassifyResponse> create(@RequestBody CommodityClassifyParam param) throws ServiceException {

        logger.info("增加商品分类requst: {}", JsonUtil.toJson(param));
        validateCreate(param);
        commodityClassifyCoreService.createCommodityClassify(buildCommodityClassifyDto(param));
        return ResultUtil.success(null);
    }

    @GetMapping("/get")
    @ResponseBody
    ResponseMessage<List<CommodityClassifyResponse>> get(CommodityClassifyParam param) throws ServiceException {
        logger.info("搜索商品分类requst: {}", JsonUtil.toJson(param));
        List<CommodityClassifyResponse> responses = commodityClassifyCoreService.getCommodityClassify(new CommodityClassifyQuery())
                .stream()
                .map(this::buildCommodityClassifyResponse)
                .collect(Collectors.toList());
        logger.info("搜索商品分类response: {}", JsonUtil.toJson(responses));
        return ResultUtil.success(responses);
    }

    private CommodityClassifyResponse buildCommodityClassifyResponse(CommodityClassifyDto dto) {
        CommodityClassifyResponse commodityClassifyResponse = new CommodityClassifyResponse();
        commodityClassifyResponse.setId(dto.getId());
        commodityClassifyResponse.setName(dto.getName());
        commodityClassifyResponse.setCreatedAt(dto.getCreatedAt());
        commodityClassifyResponse.setUpdatedAt(dto.getUpdatedAt());
        return commodityClassifyResponse;
    }

    private CommodityClassifyDto buildCommodityClassifyDto(CommodityClassifyParam param) {
        CommodityClassifyDto commodityClassifyDto = new CommodityClassifyDto();
        commodityClassifyDto.setId(param.getId());
        commodityClassifyDto.setName(param.getName());
        commodityClassifyDto.setScore(param.getScore());
        return commodityClassifyDto;
    }

    private void validateCreate(CommodityClassifyParam param) throws ServiceException {
        if(param == null
        || param.getName() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }
}
