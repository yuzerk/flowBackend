package com.flower.xin.orm.dao;

import com.flower.xin.orm.dao.base.BaseDao;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

@Data
public class OrderDao extends BaseDao implements Serializable {
    private Long id;

    private Long shippingOrderId;

    private Long userId;

    private String userName;

    private String userPhone;

    private Long bossId;

    private Integer commodityPrice;

    private Integer price;

    private Long userCouponId;

    private Integer status;

    private String payOrder;

    private Integer paySuccess;

    private Integer shippingStatus;

    private Integer orderType;

    private Integer deliveryType;

    private Date payExpireAt;

    private Date customerDeliveryAt;

    private Date createdAt;

    private Date updatedAt;

    private static final long serialVersionUID = 1L;
}
