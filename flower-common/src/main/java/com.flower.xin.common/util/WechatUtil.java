package com.flower.xin.common.util;

import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.dto.WechatLoginDto;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Service
public class WechatUtil {

    private final static Map<Long, WechatAppInfo> appInfoMap;

    private final static String AUTH_TOKEN_URL = "https://api.weixin.qq.com/sns/jscode2session";

    public static WechatLoginDto doAuthCodeSession(Long bossId, String loginCode) {
        if (!appInfoMap.containsKey(bossId)) {
            return null;
        }
        WechatAppInfo appInfo = appInfoMap.get(bossId);
        StringBuilder url = new StringBuilder(AUTH_TOKEN_URL).append("?appid=")
                .append(appInfo.getAppid())
                .append("&secret=")
                .append(appInfo.getSecret())
                .append("&js_code=")
                .append(loginCode)
                .append("&grant_type=")
                .append(appInfo.getGrantType());

        return processLoginResponse(HttpUtil.doGet(url.toString(), 10000, 10000));
//        return new WechatLoginDto("123123123", null, null, null, null);
    }

    static {
        appInfoMap = new HashMap<>();
        appInfoMap.put(0L, new WechatAppInfo("wx6e0fb5b2fbe115fd", "ab8bfdd39d07b12e0ec6c98e2cd0042b", "authorization_code"));
    }

    private static String processResponse(String response) {
        if (StringUtils.isEmpty(response)) {
            throw ErrorMessageEnum.AUTH_TOKEN_ERROR.getSystemException();
        }
        Map<String, Object> map = JsonUtil.fromJson(response, Map.class);
        if(map.containsKey("error_code") || map.containsKey("error_msg")) {
            Integer errCode = (Integer)map.get("error_code");
            String errMsg = (String) map.get("error_msg");
            if (!Objects.equals(errCode, 0.0)) {
                throw ErrorMessageEnum.SYSTEM_ERROR.getSystemException();
            }
        }
        return response;
    }

    private static WechatLoginDto processLoginResponse(String response) {
        response = processResponse(response);
        WechatLoginDto po = new WechatLoginDto();
        Map<String, Object> map = JsonUtil.fromJson(response, Map.class);
        po.setOpenId((String) map.get("openid"));
        po.setSessionKey((String) map.get("session_key"));
        return po;
    }
}

@Data
@NoArgsConstructor
@AllArgsConstructor
class WechatAppInfo {
    private String appid;

    private String secret;

    private String grantType;
}
