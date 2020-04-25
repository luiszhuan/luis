package com.luis.sites.wechat.http.req.utils;

import com.google.gson.Gson;
import com.luis.sites.wechat.constant.AppConstant;
import com.luis.sites.wechat.constant.WxUrlConstant;
import com.luis.sites.wechat.http.client.HttpClientPoolFactory;
import com.luis.sites.wechat.param.WxParam;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Type;
import java.util.List;

@Component
public class WxReqUtils {
    private static final Logger LOGGER = LogManager.getLogger(WxReqUtils.class);

    @Autowired
    WxParam wxParam;

    public String querySendRequest(String url, List<NameValuePair> pairList) {
        try {
            CloseableHttpClient client = HttpClientPoolFactory.getInstance().getIgnoreSslHttpClientPool();
            HttpGet httpGet = new HttpGet(replaceUrl(url, pairList));
            CloseableHttpResponse response = client.execute(httpGet);
            String result = IOUtils.toString(response.getEntity().getContent(), AppConstant.UTF_8);
            LOGGER.info("Query result: " + WxUrlConstant.GET_ACCESS_TOKEN_URL);
            return result;

        } catch (Exception e) {
            LOGGER.error("", e);
        }
        return null;
    }

    public String replaceUrl(String originalUrl, List<NameValuePair> pairList) {
        for (NameValuePair pair : pairList) {
            originalUrl = originalUrl.replace(":{" + pair.getName() + "}", pair.getValue());
        }
        return originalUrl;
    }

    public <T> T convertResponse(String responseStr, Type typeOfT) {
        LOGGER.debug("Query access token result {} {}", responseStr);
        Gson gson = new Gson();
        return gson.fromJson(responseStr, typeOfT);
    }

    public WxParam getWxParam() {
        return wxParam;
    }
}
