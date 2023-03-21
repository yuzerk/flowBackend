package com.flower.xin.common.util;

import com.flower.xin.common.enums.ResultEnum;
import com.flower.xin.common.response.base.Response;
import com.flower.xin.common.response.base.ResponseMessage;
import com.github.pagehelper.PageInfo;

public class ResultUtil {
    public static ResponseMessage success(PageInfo pageInfo, Object object) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(ResultEnum.SUCCESS.getCode());
        responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
        responseMessage.setLimit(pageInfo.getPageSize());
        responseMessage.setPage(pageInfo.getPageNum() + 1);
        responseMessage.setTotal(pageInfo.getTotal());
        responseMessage.setData(object);
        return responseMessage;
    }

    public static ResponseMessage success(Object object) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(ResultEnum.SUCCESS.getCode());
        responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
        if (object instanceof PageInfo) {
            PageInfo pageInfo = (PageInfo) object;
            responseMessage.setLimit(pageInfo.getPageSize());
            responseMessage.setPage(pageInfo.getPageNum() + 1);
            responseMessage.setTotal(pageInfo.getTotal());
            responseMessage.setData(pageInfo.getList());
        } else {
            responseMessage.setData(object);
        }
        return responseMessage;
    }

//    public static ResponseMessage success(PageInfo object) {
//        ResponseMessage responseMessage = new ResponseMessage();
//        responseMessage.setCode(ResultEnum.SUCCESS.getCode());
//        responseMessage.setMsg(ResultEnum.SUCCESS.getMsg());
//        responseMessage.setData(object);
//        return responseMessage;
//    }

    /**
     * 操作成功不返回消息
     *
     * @return
     */
    public static ResponseMessage success() {
        return success(null);
    }

    /**
     * 操作失败返回的消息
     *
     * @param code
     * @param msg
     * @return
     */
    public static ResponseMessage error(int code, String msg) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(code);
        responseMessage.setMsg(msg);
        return responseMessage;
    }

    /**
     * 操作失败返回消息，对error的重载
     *
     * @param resultEnum
     * @return
     */
    public static ResponseMessage error(ResultEnum resultEnum) {
        ResponseMessage responseMessage = new ResponseMessage();
        responseMessage.setCode(resultEnum.getCode());
        responseMessage.setMsg(resultEnum.getMsg());
        return responseMessage;
    }
}
