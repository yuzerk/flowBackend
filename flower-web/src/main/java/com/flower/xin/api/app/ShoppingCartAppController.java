package com.flower.xin.api.app;

import com.flower.xin.common.dto.CastAccountDto;
import com.flower.xin.common.dto.CommodityDto;
import com.flower.xin.common.dto.OrderDto;
import com.flower.xin.common.dto.ShoppingCartDto;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.param.ShoppingCartParam;
import com.flower.xin.common.query.CommodityQuery;
import com.flower.xin.common.response.ShoppingCartListResponse;
import com.flower.xin.common.response.ShoppingCartResponse;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.JsonUtil;
import com.flower.xin.common.util.ResultUtil;
import com.flower.xin.service.core.CommodityCoreService;
import com.flower.xin.service.core.OrderCoreService;
import com.flower.xin.service.core.ShoppingCartCoreService;
import com.github.pagehelper.PageInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import sun.rmi.runtime.Log;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/app/shopping/cart")
public class ShoppingCartAppController {

    private final static Logger logger = LoggerFactory.getLogger(ShoppingCartAppController.class);

    @Autowired
    private ShoppingCartCoreService shoppingCartCoreService;

    @Autowired
    private CommodityCoreService commodityCoreService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private OrderCoreService orderCoreService;

    @RequestMapping("create")
    @ResponseBody
    ResponseMessage<ShoppingCartResponse> create(@RequestBody ShoppingCartParam param) throws ServiceException {
        logger.info("加入购物车requst: {}", JsonUtil.toJson(param));
        Long uid = validateUid(request);
        validateCreate(param);
        param.setUid(uid);
        ShoppingCartDto dto = buildShoppingCartDto(param);
        shoppingCartCoreService.createShoppingCart(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(new ShoppingCartResponse(shoppingCartCoreService.countCommodity(uid)));
    }

    @RequestMapping("delete")
    @ResponseBody
    ResponseMessage<ShoppingCartResponse> delete(@RequestBody ShoppingCartParam param) throws ServiceException {
        logger.info("删除购物车requst: {}", JsonUtil.toJson(param));
        validateDelete(param);
        ShoppingCartDto dto = buildShoppingCartDto(param);
        dto.setIsValidate(ValidateEnum.INVALIDATE.getCode());
        shoppingCartCoreService.deleteShoppingCart(dto);
//        LoginResponse response = buildLoginResponse(dto);
//        logger.info("response: {}", JsonUtil.toJson(response));
        return ResultUtil.success(null);
    }

    @GetMapping("count")
    @ResponseBody
    ResponseMessage<ShoppingCartResponse> count() throws ServiceException {
        Long uid = validateUid(request);
        Long count = shoppingCartCoreService.countCommodity(uid);
        return ResultUtil.success(new ShoppingCartResponse(count));
    }

    @GetMapping("list")
    @ResponseBody
    ResponseMessage<ShoppingCartListResponse> list() throws ServiceException {
        Long uid = validateUid(request);
        // 查购物车
        ShoppingCartDto dto = new ShoppingCartDto();
        dto.setUserId(uid);
        dto.setIsValidate(ValidateEnum.VALIDATE.getCode());
        List<ShoppingCartDto> shoppingCartDtos = shoppingCartCoreService.getShoppingCartList(dto);
        List<Long> commodityIds = shoppingCartDtos.stream().map(s -> s.getCommodityId()).collect(Collectors.toList());

        // 查商品详情
        CommodityQuery query = new CommodityQuery();
        query.setPage(0);
        query.setLimit(0);
        query.setIds(commodityIds);
        PageInfo<CommodityDto> pageInfo = commodityCoreService.getCommodities(query);
        return ResultUtil.success(buildShoppingCartResponseList(pageInfo.getList(), shoppingCartDtos));
    }

    private void validateDelete(ShoppingCartParam param) throws ServiceException {
        if (param == null
                || param.getUid() == null
                || (param.getId() == null && CollectionUtils.isEmpty(param.getIds()))) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private ShoppingCartDto buildShoppingCartDto(ShoppingCartParam param) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setId(param.getId());
        shoppingCartDto.setIds(param.getIds());
        shoppingCartDto.setUserId(param.getUid());
        shoppingCartDto.setCommodityId(param.getCommodityId());
        shoppingCartDto.setCommodityCount(param.getCommodityCount());
        return shoppingCartDto;
    }

    private ShoppingCartListResponse buildShoppingCartResponseList(List<CommodityDto> dtos, List<ShoppingCartDto> shoppingCartDtos) {
        List<ShoppingCartResponse> resList = new ArrayList<>();
        List<Long> cartIds = new ArrayList<>();
        Integer totalCount = 0;
        for (ShoppingCartDto shoppingCartDto : shoppingCartDtos) {
            ShoppingCartResponse shoppingCartResponse = new ShoppingCartResponse();
            BeanUtils.copyProperties(shoppingCartDto, shoppingCartResponse);
            Optional<CommodityDto> picOptional = dtos.stream().filter(c -> c.getId().equals(shoppingCartDto.getCommodityId())).findFirst();
            if (picOptional.isPresent()) {
                shoppingCartResponse.setFirstPic(picOptional.get().getFirstPic());
                shoppingCartResponse.setCommodityName(picOptional.get().getName());
                shoppingCartResponse.setOriginalPrice(picOptional.get().getOriginalPrice());
                shoppingCartResponse.setCurrentPrice(picOptional.get().getCurrentPrice());
                shoppingCartResponse.setChecked(false);
            }
            resList.add(shoppingCartResponse);
            cartIds.add(shoppingCartDto.getId());
            totalCount += shoppingCartDto.getCommodityCount();
        }
        ShoppingCartListResponse response = new ShoppingCartListResponse();
        response.setCartResponses(resList);
        OrderDto dto = new OrderDto();
        dto.setShoppingCartIds(cartIds);
        CastAccountDto accountDto = orderCoreService.castAccount(dto);
        response.setCartTotalPrice(accountDto.getResultAccount() == null? 0L : accountDto.getResultAccount());
        response.setTotalCount(totalCount);
        return response;
    }

    private void validateCreate(ShoppingCartParam param) throws ServiceException {
        if (param == null
                || param.getCommodityId() == null
                || param.getCommodityCount() == null) {
            throw ErrorMessageEnum.MISSING_PARAM_ERROR.getServiceException();
        }
    }

    private Long validateUid(HttpServletRequest request) throws ServiceException{
        String uid = request.getHeader("uid");
        if (StringUtils.isEmpty(uid)) {
            throw ErrorMessageEnum.AUTH_TOKEN_ERROR.getServiceException();
        }
        return Long.parseLong(uid);
    }
}
