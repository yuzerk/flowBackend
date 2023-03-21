package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.*;
import com.flower.xin.common.enums.OrderStatusEnum;
import com.flower.xin.common.enums.ShippingOrderStatusEnum;
import com.flower.xin.common.enums.ValidateEnum;
import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.query.CommodityQuery;
import com.flower.xin.common.query.OrderCommodityQuery;
import com.flower.xin.common.query.OrderQuery;
import com.flower.xin.common.util.MoneyUtil;
import com.flower.xin.common.util.TimeUtil;
import com.flower.xin.orm.dao.OrderDao;
import com.flower.xin.orm.dao.OrderStatusLogDao;
import com.flower.xin.orm.impl.OrderDaoService;
import com.flower.xin.orm.impl.OrderStatusLogDaoService;
import com.flower.xin.service.core.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class OrderCoreServiceImpl implements OrderCoreService {

    @Autowired
    private ShoppingCartCoreService shoppingCartCoreService;

    @Autowired
    private CommodityCoreService commodityCoreService;

    @Autowired
    private UserCouponCoreService userCouponCoreService;

    @Autowired
    private OrderCommodityCoreServiceImpl orderCommodityCoreService;

    @Autowired
    private OrderDaoService orderDaoService;

    @Autowired
    private OrderStatusLogDaoService orderStatusLogDaoService;

    @Autowired
    private ShippingOrderCoreService shippingOrderCoreService;

    @Autowired
    private UserCoreService userCoreService;

    /**
     * 未支付订单10分钟过期
     */
    private static final Integer expireTime = 10;

    @Override
    public CastAccountDto castAccount(OrderDto dto) {
        CastAccountDto castAccountDto = new CastAccountDto();

        List<ShoppingCartDto> shoppingCartDtos = shoppingCartCoreService.getShoppingCartList(buildShoppingCartDto(dto));
        if (CollectionUtils.isEmpty(shoppingCartDtos)) {
            return castAccountDto;
        }

        castAccountDto.setShoppingCartDtos(shoppingCartDtos);

        if (dto.getUserCouponId() != null) {
            UserCouponDto userCouponDto = userCouponCoreService.getById(dto.getUserCouponId());
            if (userCouponDto != null && userCouponDto.getIsValidate().equals(ValidateEnum.VALIDATE.getCode())) {
                castAccountDto.setDiscounts(userCouponDto.getDiscounts());
            }
        }

        Set<Long> commonityIds = shoppingCartDtos.stream().map(ShoppingCartDto::getCommodityId).collect(Collectors.toSet());
        CommodityQuery commodityQuery = new CommodityQuery();
        commodityQuery.setIds(new ArrayList<>(commonityIds));
        List<CommodityDto> commodityDtos = commodityCoreService.getCommoditiesNotPage(commodityQuery);
        Map<Long, Float> commodityPriceMap = commodityDtos.stream().collect(Collectors.toMap(CommodityDto::getId, CommodityDto::getCurrentPrice));
        castAccountDto.setCommodityPriceMap(commodityPriceMap);
        for (ShoppingCartDto shoppingCartDto : shoppingCartDtos) {
            castAccountDto.addTotal(shoppingCartDto.getCommodityCount() * commodityPriceMap.get(shoppingCartDto.getCommodityId()));
        }
        castAccountDto.setResultAccount(castAccountDto.getTotal() - castAccountDto.getDiscounts());
        return castAccountDto;
    }

    private ShoppingCartDto buildShoppingCartDto(OrderDto dto) {
        ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
        shoppingCartDto.setIds(dto.getShoppingCartIds());
        return shoppingCartDto;
    }

    @Override
    public List<OrderDto> getOrderDetail(OrderQuery query) {
        return null;
    }

    /**
     * 1. 计算价格(后期前端加预计算价格，进行核对)
     * 2. 用户优惠券无效化
     * 3. 用户购物车无效化
     * 4. 获得用户信息
     * 5. 看是否需要配送运单，需要的话创建运单
     * 6. 创建订单
     * 7. 更新运单表，添加orderId
     * 8. 创建订单商品表
     * 9. 记录订单状态变更
     *
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void createOrder(OrderDto dto) throws ServiceException {
        ShippingOrderDto shippingOrderDto = new ShippingOrderDto();

        //1
        CastAccountDto castAccountDto = castAccount(dto);

        //2
        userCouponCoreService.invalidateUserCopon(dto.getUserCouponId());

        //3
        shoppingCartCoreService.invalidateShoppingCart(buildShoppingCartDto(dto));

        //4
        UserDto userDto = userCoreService.getUser(dto.getUserId());
        if (StringUtils.isEmpty(userDto.getPhoneNum()) || StringUtils.isEmpty(userDto.getUserName())) {
            throw ErrorMessageEnum.MISSING_USER_INFO_ERROR.getServiceException();
        }

        //5 创建运单
        shippingOrderDto = buildShippingOrderDto(dto, userDto, ShippingOrderStatusEnum.CREATE);
        //因为订单还没落库，下面会更新运单表，更新orderId字段
        shippingOrderDto.setOrderId(0L);
        shippingOrderCoreService.createShippingOrder(shippingOrderDto);

        //6
        dto.setStatus(OrderStatusEnum.WAIT_PAY.getCode());
        dto.setShippingOrderId(shippingOrderDto.getId());
        dto.setPayExpireAt(TimeUtil.getDateAddMinutes(new Date(), expireTime));
        buildPriceOrderDto(castAccountDto, dto);
        buildUserInfoOrderDto(userDto, dto);
        OrderDao orderDao = buildOrderDto(dto);
        orderDaoService.createOrder(orderDao);
        dto.setId(orderDao.getId());

        //7
        ShippingOrderDto updateShippingOrderDto = new ShippingOrderDto();
        updateShippingOrderDto.setOrderId(dto.getId());
        updateShippingOrderDto.setId(shippingOrderDto.getId());
        shippingOrderCoreService.updateShippingOrder(updateShippingOrderDto);

        //8
        for (ShoppingCartDto shoppingCart : castAccountDto.getShoppingCartDtos()) {
            OrderCommodityDto orderCommodityDto = new OrderCommodityDto();
            orderCommodityDto.setOrderId(dto.getId());
            orderCommodityDto.setShoppingCartId(shoppingCart.getId());
            orderCommodityDto.setUserId(dto.getUserId());
            orderCommodityDto.setPrice(castAccountDto.getCommodityPrice(shoppingCart.getCommodityId()) * shoppingCart.getCommodityCount());
            orderCommodityCoreService.createOrderCommodity(orderCommodityDto);
        }

        //9
        orderStatusLogDaoService.createOrderStatusLog(buildOrderStatusLogDao(dto.getId(), dto.getStatus()));
    }

    /**
     * 1. 是否待支付订单
     * 2. 优惠券恢复
     * 3. 购物车恢复
     * 4. 订单状态变更
     * 5. 订单状态变更表
     * @param dto
     * @throws ServiceException
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void cancelOrder(OrderDto dto) throws ServiceException {
        OrderDao orderDao = orderDaoService.getOrder(dto.getId());
        OrderStatusEnum orderStatus = OrderStatusEnum.CANCEL;
        //1
        if(!orderDao.getStatus().equals(OrderStatusEnum.WAIT_PAY.getCode())) {
            throw ErrorMessageEnum.ORDER_CAN_NOT_CANCEL_ERROR.getServiceException();
        }

        //2
        userCouponCoreService.validateUserCopon(dto.getUserCouponId());

        //3
        OrderCommodityQuery orderCommodityQuery = new OrderCommodityQuery();
        orderCommodityQuery.setOrderId(orderDao.getId());
        List<OrderCommodityDto> orderCommodityDtos = orderCommodityCoreService.getOrderCommodities(orderCommodityQuery);
        if(!CollectionUtils.isEmpty(orderCommodityDtos)) {
            List<Long> shoppingCartIds = orderCommodityDtos.stream().map(OrderCommodityDto::getShoppingCartId).collect(Collectors.toList());
            ShoppingCartDto shoppingCartDto = new ShoppingCartDto();
            shoppingCartDto.setIds(shoppingCartIds);
            shoppingCartCoreService.validateShoppingCart(shoppingCartDto);
        }

        //4 & 5
        OrderDao updateOrderDao = new OrderDao();
        updateOrderDao.setId(orderDao.getId());
        updateOrderDao.setStatus(orderStatus.getCode());
        updateOrder(orderDao, orderStatus);
    }

    @Transactional(rollbackFor = Exception.class)
    public void updateOrder(OrderDao orderDao, OrderStatusEnum orderStatus) {
        orderDaoService.updateOrder(orderDao);
        orderStatusLogDaoService.createOrderStatusLog(buildOrderStatusLogDao(orderDao.getId(), orderStatus.getCode()));
    }

    /**
     * 1. 判断是否待支付订单
     * 2. 订单是否过期，过期自动取消，走订单取消逻辑
     * 3. 走微信支付逻辑
     * 4. 订单表更新
     * 5. 订单状态表更新
     * @param dto
     */
    @Override
    public void payOrder(OrderDto dto) throws ServiceException {
        OrderDao orderDao = orderDaoService.getOrder(dto.getId());
        OrderStatusEnum orderStatus = OrderStatusEnum.PAYING;
        //1
        if(!orderDao.getStatus().equals(OrderStatusEnum.WAIT_PAY.getCode())) {
            throw ErrorMessageEnum.ORDER_CAN_NOT_PAY_ERROR.getServiceException();
        }

        //2
        if(orderDao.getPayExpireAt().getTime() < System.currentTimeMillis()) {
            cancelOrder(dto);
            throw ErrorMessageEnum.ORDER_PAY_EXPIRE_ERROR.getServiceException();
        }

        //3
        // TODO: 2020/4/14 微信支付逻辑 下面还需要新插入支付相关凭证信息


        //4 & 5
        OrderDao updateOrderDao = new OrderDao();
        updateOrderDao.setId(orderDao.getId());
        updateOrderDao.setStatus(orderStatus.getCode());
        updateOrder(updateOrderDao, orderStatus);

    }


    /**
     * 1. 订单表状态更新
     * 2. 运单表状态更新
     * 3. 订单状态变更表
     * @param dto
     */
    @Override
    @Transactional(rollbackFor = Exception.class)
    public void completeOrder(OrderDto dto) {
        OrderDao orderDao = orderDaoService.getOrder(dto.getId());

        OrderStatusEnum orderStatus = OrderStatusEnum.COMPLETE;
        ShippingOrderStatusEnum shippingOrderStatus = ShippingOrderStatusEnum.COMPLETE;

        OrderDao updateOrderDao = new OrderDao();
        updateOrderDao.setId(dto.getId());
        updateOrderDao.setStatus(orderStatus.getCode());
        updateOrderDao.setShippingStatus(shippingOrderStatus.getCode());

        //1
        updateOrder(orderDao, orderStatus);

        //2
        ShippingOrderDto shippingOrderDto = new ShippingOrderDto();
        shippingOrderDto.setId(orderDao.getShippingOrderId());
        shippingOrderDto.setStatus(shippingOrderStatus.getCode());
        shippingOrderCoreService.updateShippingOrder(shippingOrderDto);
    }

    private void buildUserInfoOrderDto(UserDto userDto, OrderDto dto) {
        dto.setUserName(userDto.getUserName());
        dto.setUserPhone(userDto.getPhoneNum());
    }

    private ShippingOrderDto buildShippingOrderDto(OrderDto dto, UserDto userDto, ShippingOrderStatusEnum shippingOrderStatus) {
        ShippingOrderDto shippingOrderDto = new ShippingOrderDto();
        shippingOrderDto.setId(dto.getId());
        shippingOrderDto.setOrderId(dto.getId());
        shippingOrderDto.setUserId(dto.getUserId());
        shippingOrderDto.setUserName(userDto.getUserName());
        shippingOrderDto.setUserPhone(userDto.getPhoneNum());
        shippingOrderDto.setCustomerId(0L);
        shippingOrderDto.setCustomerName(dto.getCustomerName());
        shippingOrderDto.setCustomerPhone(dto.getCustomerPhone());
        shippingOrderDto.setCustomerAddress(dto.getCustomerAddress());
        shippingOrderDto.setStatus(shippingOrderStatus.getCode());
        return shippingOrderDto;
    }

    private OrderStatusLogDao buildOrderStatusLogDao(Long orderId, Integer status) {
        OrderStatusLogDao dao = new OrderStatusLogDao();
        dao.setOrderId(orderId);
        dao.setStatus(status);
        return dao;
    }

    private OrderDao buildOrderDto(OrderDto dto) {
        OrderDao orderDao = new OrderDao();
        orderDao.setId(dto.getId());
        orderDao.setShippingOrderId(dto.getShippingOrderId());
        orderDao.setUserId(dto.getUserId());
        orderDao.setUserName(dto.getUserName());
        orderDao.setUserPhone(dto.getUserPhone());
        orderDao.setBossId(dto.getBossId());
        orderDao.setCommodityPrice(MoneyUtil.yuanToFen(dto.getCommodityPrice()));
        orderDao.setPrice(MoneyUtil.yuanToFen(dto.getPrice()));
        orderDao.setUserCouponId(dto.getUserCouponId());
        orderDao.setStatus(dto.getStatus());
        orderDao.setPayOrder(dto.getPayOrder());
        orderDao.setPaySuccess(dto.getPaySuccess());
        orderDao.setShippingStatus(dto.getShippingStatus());
        orderDao.setOrderType(dto.getOrderType());
        orderDao.setDeliveryType(dto.getDeliveryType());
        orderDao.setPayExpireAt(dto.getPayExpireAt());
        orderDao.setCustomerDeliveryAt(dto.getCustomerDeliveryAt());
        return orderDao;
    }

    private void buildPriceOrderDto(CastAccountDto castAccountDto, OrderDto dto) {
        dto.setPrice(castAccountDto.getResultAccount());
        dto.setCommodityPrice(castAccountDto.getTotal());
    }

    @Override
    public void refundOrder(OrderDto dto) {

    }

    @Override
    public void doRefund(OrderDto dto) {

    }
}
