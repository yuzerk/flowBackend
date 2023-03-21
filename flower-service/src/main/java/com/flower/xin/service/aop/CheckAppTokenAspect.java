package com.flower.xin.service.aop;

import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.orm.dao.UserTokenDao;
import com.flower.xin.orm.impl.UserTokenDaoService;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.util.Objects;
import java.util.Optional;

/**
 * @author eiven
 */
@Aspect
@Component
public class CheckAppTokenAspect {

    @Autowired
    UserTokenDaoService userTokenDaoService;
//
//    @Around("@annotation(com.flower.xin.service.annotation.CheckAppToken)")
//    public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
//        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//        Method target = methodSignature.getMethod();
//        String methodName = target.getDeclaringClass() + "$" + target.getName();
//        Object[] args = jp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            if (args[i] instanceof RequestParam) {
//                RequestParam requestParam = (RequestParam) args[i];
//                if (requestParam == null
//                        || StringUtils.isEmpty(requestParam.getAppToken())
//                        || requestParam.getUid() == null) {
//                    throw ErrorMessageEnum.USER_TOKEN_ERROR.getSystemException();
//                }

//            }
//        }
//        //执行原有业务逻辑
//        try {
//            Object obj = jp.proceed();
////            logger.info("API【{}】返回结果为{}", methodName, JsonUtils.toJson(obj));
//            return obj;
//        } catch (InvocationTargetException e) {
//            //抛出原先应有的exception
//            throw e.getTargetException();
//        }
//    }

    private HttpServletRequest currentRequest() {
        // Use getRequestAttributes because of its return null if none bound
        ServletRequestAttributes servletRequestAttributes = (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();
        return Optional.ofNullable(servletRequestAttributes).map(ServletRequestAttributes::getRequest).orElse(null);
    }

    @Around("@annotation(com.flower.xin.service.annotation.CheckAppToken)")
    public Object aroundMethod(ProceedingJoinPoint jp) throws Throwable {
        HttpServletRequest request = currentRequest();
        if (Objects.isNull(request)) {
//            log.info("without request, skip");
            throw ErrorMessageEnum.USER_TOKEN_ERROR.getSystemException();
        }

        String token = request.getHeader("appToken");
        String uid = request.getHeader("uid");

        if (StringUtils.isEmpty(token) || StringUtils.isEmpty(uid)) {
            throw ErrorMessageEnum.USER_TOKEN_EXPIRE_ERROR.getSystemException();
        }

        UserTokenDao userTokenDao = userTokenDaoService.getToken(Long.parseLong(uid));
        if (userTokenDao == null) {
            throw ErrorMessageEnum.USER_TOKEN_EXPIRE_ERROR.getSystemException();
        }
        if (userTokenDao.getExpireAt().getTime() < System.currentTimeMillis()) {
            throw ErrorMessageEnum.USER_TOKEN_EXPIRE_ERROR.getSystemException();
        }
        if (!userTokenDao.getToken().equals(token)) {
            throw ErrorMessageEnum.USER_TOKEN_EXPIRE_ERROR.getSystemException();
        }

        Object obj = jp.proceed();
        return obj;

//
//
//        MethodSignature methodSignature = (MethodSignature) jp.getSignature();
//        Method target = methodSignature.getMethod();
//        String methodName = target.getDeclaringClass() + "$" + target.getName();
//        Object[] args = jp.getArgs();
//        for (int i = 0; i < args.length; i++) {
//            if (args[i] instanceof RequestParam) {
//                RequestParam requestParam = (RequestParam) args[i];
//                if (requestParam == null
//                        || StringUtils.isEmpty(requestParam.getAppToken())
//                        || requestParam.getUid() == null) {
//                    throw ErrorMessageEnum.USER_TOKEN_ERROR.getSystemException();
//                }
//                UserTokenDao userTokenDao = userTokenDaoService.getToken(requestParam.getUid());
//                if (userTokenDao == null) {
//                    throw ErrorMessageEnum.USER_NOT_REGISTER_ERROR.getSystemException();
//                }
//                if (userTokenDao.getExpireAt().getTime() < System.currentTimeMillis()) {
//                    throw ErrorMessageEnum.USER_TOKEN_EXPIRE_ERROR.getSystemException();
//                }
//                if (!userTokenDao.getToken().equals(requestParam.getAppToken())) {
//                    throw ErrorMessageEnum.USER_TOKEN_ERROR.getSystemException();
//                }
//            }
//        }
//        //执行原有业务逻辑
//        try {
//            Object obj = jp.proceed();
////            logger.info("API【{}】返回结果为{}", methodName, JsonUtils.toJson(obj));
//            return obj;
//        } catch (InvocationTargetException e) {
//            //抛出原先应有的exception
//            throw e.getTargetException();
//        }
    }
}
