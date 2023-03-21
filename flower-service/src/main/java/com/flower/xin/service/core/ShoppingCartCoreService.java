package com.flower.xin.service.core;

import com.flower.xin.common.dto.ShoppingCartDto;

import java.util.List;

public interface ShoppingCartCoreService {
    void createShoppingCart(ShoppingCartDto dto);

    void deleteShoppingCart(ShoppingCartDto dto);

    List<ShoppingCartDto> getShoppingCartList(ShoppingCartDto dto);

    void invalidateShoppingCart(ShoppingCartDto dto);

    void validateShoppingCart(ShoppingCartDto dto);

    Long countCommodity(Long uid);
}
