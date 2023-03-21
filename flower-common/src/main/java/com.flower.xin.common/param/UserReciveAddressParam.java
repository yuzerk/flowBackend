package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;

@Data
public class UserReciveAddressParam extends RequestParam {
    private Long id;

    private String customerName;

    private String customerPhone;

    private String province;

    private String slCity;

    private String tlCity;

    private String street;

    private String addressDetail;

    private Integer isValidate;

    private Date createdAt;

    private Date updatedAt;
}
