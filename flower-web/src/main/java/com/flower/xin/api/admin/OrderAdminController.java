package com.flower.xin.api.admin;


import com.flower.xin.common.dto.CommoditySubjectContentDto;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.CommoditySubjectContentParam;
import com.flower.xin.common.param.OrderParam;
import com.flower.xin.common.response.CommoditySubjectContentResponse;
import com.flower.xin.common.response.OrderResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * @author eiven
 */
@CrossOrigin
@Controller
@RequestMapping("/admin/order")
public class OrderAdminController {
    private final static Logger logger = LoggerFactory.getLogger(OrderAdminController.class);

    @RequestMapping("/get")
    @ResponseBody
    ResponseMessage<OrderResponse> get(@RequestBody OrderParam param) throws ServiceException {
        logger.info("查询订单requst: {}", JsonUtil.toJson(param));
        validateGet(param);
//        CommoditySubjectContentDto dto = buildCommoditySubjectContentDto(param);
//        commoditySubjectContentCoreService.updateCommoditySubject(dto);
        return ResultUtil.success(null);
    }

    private void validateGet(OrderParam param) throws ServiceException {
        if (param == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

}
