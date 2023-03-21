package com.flower.xin.common.param;

import com.flower.xin.common.param.base.RequestParam;
import lombok.Data;

import java.util.Date;

/**
 * @author eiven
 */
@Data
public class CommodityClassifyParam extends RequestParam {
    private Long id;

    private String name;

    private Integer score;
}
