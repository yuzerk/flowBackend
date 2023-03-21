package com.flower.xin.common.response;

import com.flower.xin.common.response.base.Response;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
 * @author eiven
 */
@Data
public class CommodityClassifyResponse {
    private Long id;

    private String name;

    private Date createdAt;

    private Date updatedAt;
}
