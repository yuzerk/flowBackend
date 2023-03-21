package com.flower.xin.common.dto;

import lombok.Data;

import java.util.List;
import java.util.Map;

@Data
public class CastAccountDto {
    private Float discounts = 0F;

    private Float total = 0F;

    private Float resultAccount = 0F;

    private Map<Long, Float> commodityPriceMap;

    private List<ShoppingCartDto> shoppingCartDtos;

    public void addTotal(Float amount) {
        total += amount;
    }

    public Float getCommodityPrice(Long commodityId) {
        if(commodityPriceMap.containsKey(commodityId)) {
            return commodityPriceMap.get(commodityId);
        }
        return 0F;
    }
}
