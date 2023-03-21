package com.flower.xin.service.core;

import com.flower.xin.common.dto.CastAccountDto;
import com.flower.xin.common.dto.OrderDto;
import com.flower.xin.common.dto.ShoppingCartDto;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.query.OrderQuery;

import java.util.List;

public interface OrderCoreService {


    /**
     * 查询订单
     * @param query
     * @return
     */
    List<OrderDto> getOrderDetail(OrderQuery query);
    /**
     * 创建订单
     * @param dto
     */
    void createOrder(OrderDto dto) throws ServiceException;

    /**
     * 用户支付
     * @param dto
     */
    void payOrder(OrderDto dto) throws ServiceException;

    /**
     * 用户取消支付订单
     * @param dto
     */
    void cancelOrder(OrderDto dto) throws ServiceException;

    /**
     * 用户发起退款
     * @param dto
     */
    void refundOrder(OrderDto dto);

    /**
     * 订单完成
     * @param dto
     */
    void completeOrder(OrderDto dto);

    /**
     * 操作退款
     * @param dto
     */
    void doRefund(OrderDto dto);

    /**
     * 价格计算
     * @param dto
     * @return
     */
    CastAccountDto castAccount(OrderDto dto);
}
