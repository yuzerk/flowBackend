package com.flower.xin.common.enums;

public enum OrderStatusEnum {
    CREATE(0, "创建订单"),
    CANCEL(1, "取消订单"),
    WAIT_PAY(10, "待支付"),
    PAYING(11, "支付中"),
    WAIT_DISPATCH(20, "待配送"),
    DISPATCHING(30, "配送中"),
    FAIL(80, "失败"),
    COMPLETE(90, "完成"),
    WAIT_REFUND(100, "待退款"),
    REFUNDED(110, "已退款");

    private Integer code;

    private String msg;

    OrderStatusEnum(Integer code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Integer getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
    }
