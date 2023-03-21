package com.flower.xin.common.param.base;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author eiven
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RequestParam {
    private String appToken;

    private Long uid;

    private Integer limit;

    private Integer page;
}
