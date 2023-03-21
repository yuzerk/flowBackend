package com.flower.xin.service.core.impl;

import com.flower.xin.common.dto.ShippingOrderDto;
import com.flower.xin.orm.dao.ShippingOrderDao;
import com.flower.xin.orm.impl.ShippingOrderDaoService;
import com.flower.xin.service.core.ShippingOrderCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author eiven
 */
@Service
public class ShippingOrderCoreServiceImpl implements ShippingOrderCoreService {

    @Autowired
    private ShippingOrderDaoService shippingOrderDaoService;

    @Override
    public void createShippingOrder(ShippingOrderDto dto) {
        ShippingOrderDao shippingOrderDao = buildShippingOrderDao(dto);
        shippingOrderDaoService.createShippingOrder(shippingOrderDao);
        dto.setId(shippingOrderDao.getId());
    }

    private ShippingOrderDao buildShippingOrderDao(ShippingOrderDto dto) {
        ShippingOrderDao shippingOrderDao = new ShippingOrderDao();
        shippingOrderDao.setId(dto.getId());
        shippingOrderDao.setOrderId(dto.getOrderId());
        shippingOrderDao.setUserId(dto.getUserId());
        shippingOrderDao.setUserName(dto.getUserName());
        shippingOrderDao.setUserPhone(dto.getUserPhone());
        shippingOrderDao.setCustomerId(dto.getCustomerId());
        shippingOrderDao.setCustomerName(dto.getCustomerName());
        shippingOrderDao.setCustomerPhone(dto.getCustomerPhone());
        shippingOrderDao.setCustomerAddress(dto.getCustomerAddress());
        shippingOrderDao.setLogisticsCompany(dto.getLogisticsCompany());
        shippingOrderDao.setExtenerShipppingId(dto.getExtenerShipppingId());
        shippingOrderDao.setFreight(dto.getFreight());
        shippingOrderDao.setStatus(dto.getStatus());
        return shippingOrderDao;
    }

    @Override
    public void waitDispatchShippingOrder(ShippingOrderDto dto) {

    }

    @Override
    public void dispatchingShippingOrder(ShippingOrderDto dto) {

    }

    @Override
    public void completeShippingOrder(ShippingOrderDto dto) {

    }

    @Override
    public void cancelShippingOrder(ShippingOrderDto dto) {

    }

    @Override
    public void updateShippingOrder(ShippingOrderDto dto) {
        shippingOrderDaoService.updateShippingOrder(buildShippingOrderDao(dto));
    }
}
