package com.flower.xin.common.dto;

import lombok.Data;

/**
 * @author eiven
 */
@Data
public class UserReceiveAddressDto {
    private Long id;

    private Long userId;

    private String customerName;

    private String customerPhone;

    private String province;

    private String slCity;

    private String tlCity;

    private String street;

    private String addressDetail;

    private Integer isValidate;
}
