package com.flower.xin.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ShoppingCartResponse {

    private Long count;

    private Long id;

    private Long userId;

    private Long commodityId;

    private Integer commodityCount;

    private String firstPic;

    private String commodityName;

    private Float originalPrice;

    private Float currentPrice;

    private Boolean checked;

    public ShoppingCartResponse(Long count) {
        this.count = count;
    }
}
