package com.flower.xin.common.response;

import lombok.Data;

/**
 * @author eiven
 */
@Data
public class LoginResponse {
    private Long uid;

    private String appToken;
}
