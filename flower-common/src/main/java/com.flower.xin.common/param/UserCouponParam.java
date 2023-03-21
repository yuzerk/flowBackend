package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

@Data
public class UserCouponParam extends RequestParam {
    private Long id;

    private Long couponCategoryId;
}
