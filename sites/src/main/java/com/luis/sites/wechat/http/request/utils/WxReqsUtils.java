package com.luis.sites.wechat.http.request.utils;

import com.google.gson.Gson;
import com.luis.sites.wechat.constant.AppConstant;
import com.luis.sites.wechat.constant.WxUrlConstant;
import com.luis.sites.wechat.http.client.HttpClientFactory;
import com.luis.sites.wechat.param.WxParam;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WxReqsUtils {
    @Autowired
    WxParam wxParam;

    public String queryAccessToken() {
        try {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
            BasicNameValuePair appId = new BasicNameValuePair(WxUrlConstant.URL_PARAM_APP_ID, wxParam.getAppId());
            BasicNameValuePair appSecret = new BasicNameValuePair(WxUrlConstant.URL_PARAM_APP_SECRET, wxParam.getAppSecret());
            pairList.add(appId);
            pairList.add(appSecret);
            CloseableHttpClient client = HttpClientFactory.getInstance().getIgnoreSSLClient();
            HttpGet httpGet = new HttpGet(replaceUrl(WxUrlConstant.GET_ACCESS_TOKEN_URL, pairList));
            CloseableHttpResponse response = client.execute(httpGet);
            String result = IOUtils.toString(response.getEntity().getContent(), AppConstant.UTF_8);
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    public String replaceUrl(String originalUrl, List<NameValuePair> pairList) {
        for (NameValuePair pair : pairList) {
            originalUrl = originalUrl.replace(":{" + pair.getName() + "}", pair.getValue());
        }
        return originalUrl;
    }

    public WxAccessTokenResp convertResponse(String responseStr) {
        Gson gson = new Gson();
        return gson.fromJson(responseStr, WxAccessTokenResp.class);
    }
}
