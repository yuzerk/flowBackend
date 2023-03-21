package com.flower.xin.common.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eiven
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class WechatLoginDto {
    private String openId;

    private String sessionKey;

    private String unionId;

    private Integer errCode;

    private String errMsg;
}
