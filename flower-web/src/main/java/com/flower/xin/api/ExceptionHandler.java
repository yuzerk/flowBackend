package com.flower.xin.api;

import com.flower.xin.api.app.UserAppController;
import com.flower.xin.common.enums.ResultEnum;
import com.flower.xin.common.exception.ServiceException;
import com.flower.xin.common.exception.SystemException;
import com.flower.xin.common.response.base.ResponseMessage;
import com.flower.xin.common.util.ResultUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ResponseBody;

@ControllerAdvice
public class ExceptionHandler {
    private final static Logger logger = LoggerFactory.getLogger(UserAppController.class);

    @org.springframework.web.bind.annotation.ExceptionHandler(value = Exception.class)
    @ResponseBody
    public ResponseMessage handel(Exception e){
        if(e instanceof ServiceException){
            ServiceException myException =(ServiceException)e;
            logger.error("[ServiceException异常] {}",e);
            return ResultUtil.error( Integer.parseInt(myException.getCode()), myException.getMessage());
        }else if(e instanceof SystemException){
            logger.error("[SystemException异常] {}",e);
            SystemException myException =(SystemException)e;
            return ResultUtil.error( Integer.parseInt(myException.getCode()), myException.getMessage());
        } else {
            logger.error("[系统异常] {}",e);
            return ResultUtil.error(ResultEnum.SYSTEM_ERROR);
        }
    }
}
