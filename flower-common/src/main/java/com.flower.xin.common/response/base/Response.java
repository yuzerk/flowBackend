package com.flower.xin.common.response.base;

import com.flower.xin.common.exception.ErrorMessageEnum;
import lombok.Data;

@Data
public class Response<T> {
    private Integer errno;

    private String errMsg;

    private T data;

    public Response() {
        this.errno = Integer.parseInt(ErrorMessageEnum.SUCCESS.getCode());
        this.errMsg = ErrorMessageEnum.SUCCESS.getMsg();
    }

    public Response(Integer errno, String errMsg) {
        this.errno = errno;
        this.errMsg = errMsg;
    }
}
