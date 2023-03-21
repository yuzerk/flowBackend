package com.flower.xin.orm.dao;

import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class ShippingOrderDao implements Serializable {
    private Long id;

    private Long orderId;

    private Long userId;

    private String userName;

    private String userPhone;

    private Long customerId;

    private String customerName;

    private String customerPhone;

    private String customerAddress;

    private String logisticsCompany;

    private String extenerShipppingId;

    private Integer freight;

    private Integer status;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
