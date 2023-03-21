package com.flower.xin.common.util;

import com.flower.xin.common.exception.ErrorMessageEnum;
import com.flower.xin.common.exception.SystemException;
import org.apache.http.Header;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.message.BasicHeader;
import org.apache.http.util.EntityUtils;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;

/**
 * @author eiven
 */
public class HttpUtil {
    public static String doPost(String uri, Header[] headers, String jsonStr, int connectTimeOut, int socketTimeOut) {

        HttpPost httpPost = new HttpPost(uri);
        try {
            //配置StringEntity
            httpPost.setEntity(getStringEntity(jsonStr));
            httpPost.setHeaders(headers);
            //配置连接超时
            httpPost.setConfig(getRequestConfig(connectTimeOut, socketTimeOut));
            //执行操作
            HttpResponse response = HttpClients.createDefault().execute(httpPost);

//            logger.info("调用外部返回status:{},", response.getStatusLine().getStatusCode());

            if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                // 借助EntityUtils的toString()方法对 HttpEntity对象进行处理，返回字符串。
                return EntityUtils.toString(response.getEntity(), "UTF-8");
            }
        } catch (Exception e) {
//            logger.error("调用接口:uri={},jsonStr={},返回失败", uri, jsonStr, e);
            throw ErrorMessageEnum.SYSTEM_ERROR.getSystemException();
        }
        return null;
    }

    public static String doPost(String uri, Header[] headers, List<NameValuePair> postParams, int connectTimeOut, int socketTimeOut) {
        HttpPost httpPost = new HttpPost(uri);

        try {
            httpPost.setHeaders(headers);
            httpPost.setEntity(new UrlEncodedFormEntity(postParams, "UTF-8"));

            //配置连接超时
            httpPost.setConfig(getRequestConfig(connectTimeOut, socketTimeOut));

            HttpResponse response = HttpClients.createDefault().execute(httpPost);

            if (response != null && HttpStatus.SC_OK == response.getStatusLine().getStatusCode()) {
                // 借助EntityUtils的toString()方法对 HttpEntity对象进行处理，返回字符串。
                return EntityUtils.toString(response.getEntity());
            }
        } catch (Exception e) {
//            logger.error("调用接口:uri={},postParams={},返回失败", uri, postParams, e);
            throw ErrorMessageEnum.SYSTEM_ERROR.getSystemException();
        }
        return null;
    }

    public static String doGet(String url, int connectTimeOut, int socketTimeOut) {
        HttpGet httpGet = new HttpGet(url);
        String result = null;
        try {
            httpGet.setHeaders(new BasicHeader[]{new BasicHeader("Accept", "application/json")});
            httpGet.setConfig(getRequestConfig(connectTimeOut, socketTimeOut));
            CloseableHttpResponse response = HttpClients.createDefault().execute(httpGet);
            if (response.getStatusLine().getStatusCode() == 200) {
                result = EntityUtils.toString(response.getEntity());
            }
        } catch (IOException e) {
//            logger.error("调用接口:url={}, 返回失败", url, e);
            throw ErrorMessageEnum.SYSTEM_ERROR.getSystemException();
        }
        return result;
    }

    private static StringEntity getStringEntity(String jsonStr) {
        StringEntity stringEntity = new StringEntity(jsonStr, "UTF-8");
        stringEntity.setContentType("application/json");
        stringEntity.setContentEncoding("UTF-8");
        return stringEntity;
    }

    private static RequestConfig getRequestConfig(int connectTimeOut, int socketTimeOut) {
        return RequestConfig
                .custom()
                .setSocketTimeout(socketTimeOut)
                .setConnectTimeout(connectTimeOut).build();
    }
}
