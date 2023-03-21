package com.flower.xin.common.response.base;

import lombok.Data;

@Data
public class ResponseMessage<T> {
    //错误码
    private int code;

    //信息描述
    private String msg;

    private Integer page;

    private Integer limit;

    private Long total;

    //具体的信息内容
    private T data;

    public ResponseMessage success(T data) {
        ResponseMessage message = new ResponseMessage();
        message.setCode(200);
        message.setMsg("SUCCESS");
        message.setData(data);
        return message;
    }
}
