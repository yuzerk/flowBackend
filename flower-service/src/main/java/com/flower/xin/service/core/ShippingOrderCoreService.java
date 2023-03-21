package com.flower.xin.service.core;

import com.flower.xin.common.dto.ShippingOrderDto;

public interface ShippingOrderCoreService {
    void createShippingOrder(ShippingOrderDto dto);

    void waitDispatchShippingOrder(ShippingOrderDto dto);

    void dispatchingShippingOrder(ShippingOrderDto dto);

    void completeShippingOrder(ShippingOrderDto dto);

    void cancelShippingOrder(ShippingOrderDto dto);

    void updateShippingOrder(ShippingOrderDto dto);
}
