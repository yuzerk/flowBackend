package com.flower.xin.common.response;

import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@NoArgsConstructor
public class ShoppingCartListResponse {

    private List<ShoppingCartResponse> cartResponses;

    private Float cartTotalPrice;

    private Integer totalCount;
}
