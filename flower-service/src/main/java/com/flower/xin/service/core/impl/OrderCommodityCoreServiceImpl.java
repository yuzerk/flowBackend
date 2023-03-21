package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.OrderCommodityDto;
import com.flower.xin.common.query.OrderCommodityQuery;
import com.flower.xin.common.util.MoneyUtil;
import com.flower.xin.orm.dao.OrderCommodityDao;
import com.flower.xin.orm.impl.OrderCommodityDaoService;
import com.flower.xin.service.core.OrderCommodityCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class OrderCommodityCoreServiceImpl implements OrderCommodityCoreService {

    @Autowired
    private OrderCommodityDaoService orderCommodityDaoService;

    @Override
    public void createOrderCommodity(OrderCommodityDto dto) {
        orderCommodityDaoService.createOrderCommodity(buildOrderCommodityDao(dto));
    }

    private OrderCommodityDao buildOrderCommodityDao(OrderCommodityDto dto) {
        OrderCommodityDao orderCommodityDao = new OrderCommodityDao();
        orderCommodityDao.setId(dto.getId());
        orderCommodityDao.setOrderId(dto.getOrderId());
        orderCommodityDao.setUserId(dto.getUserId());
        orderCommodityDao.setShoppingCartId(dto.getShoppingCartId());
        orderCommodityDao.setPrice(MoneyUtil.yuanToFen(dto.getPrice()));
        return orderCommodityDao;
    }

    @Override
    public List<OrderCommodityDto> getOrderCommodities(OrderCommodityQuery query) {
        List<OrderCommodityDao> orderCommodityDaos = orderCommodityDaoService.getOrderCommodityList(buildOrderCommodityDao(query));
        if(CollectionUtils.isEmpty(orderCommodityDaos)) {
            return Collections.EMPTY_LIST;
        }
        return orderCommodityDaos.stream().map(this::buildOrderCommodityDto).collect(Collectors.toList());
    }

    private OrderCommodityDto buildOrderCommodityDto(OrderCommodityDao orderCommodityDao) {
        OrderCommodityDto orderCommodityDto = new OrderCommodityDto();
        orderCommodityDto.setId(orderCommodityDao.getId());
        orderCommodityDto.setOrderId(orderCommodityDao.getOrderId());
        orderCommodityDto.setUserId(orderCommodityDao.getUserId());
        orderCommodityDto.setShoppingCartId(orderCommodityDao.getShoppingCartId());
        orderCommodityDto.setPrice(MoneyUtil.fenToYuan(orderCommodityDao.getPrice()));
        orderCommodityDto.setCreatedAt(orderCommodityDao.getCreatedAt());
        orderCommodityDto.setUpdatedAt(orderCommodityDao.getUpdatedAt());
        return orderCommodityDto;
    }

    private OrderCommodityDao buildOrderCommodityDao(OrderCommodityQuery query) {
        OrderCommodityDao orderCommodityDao = new OrderCommodityDao();
        orderCommodityDao.setId(query.getId());
        orderCommodityDao.setOrderId(query.getOrderId());
        orderCommodityDao.setUserId(query.getUserId());
        orderCommodityDao.setShoppingCartId(query.getShoppingCartId());
        orderCommodityDao.setPrice(query.getPrice());
        return orderCommodityDao;
    }
}
