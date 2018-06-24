package com.luis.sites.wechat.http.req.utils;

import com.luis.sites.wechat.constant.WxUrlConstant;
import com.luis.sites.wechat.http.resp.access.token.WxAccessTokenResp;
import org.apache.http.NameValuePair;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class WxAccessTokenUtils {
    private static final Logger LOGGER = LogManager.getLogger(WxAccessTokenUtils.class);

    @Autowired
    WxReqUtils wxReqUtils;

    public WxAccessTokenResp queryAccessToken() {
        try {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
            BasicNameValuePair appId = new BasicNameValuePair(WxUrlConstant.URL_PARAM_APP_ID, wxReqUtils.getWxParam().getAppId());
            BasicNameValuePair appSecret = new BasicNameValuePair(WxUrlConstant.URL_PARAM_APP_SECRET, wxReqUtils.getWxParam().getAppSecret());
            pairList.add(appId);
            pairList.add(appSecret);
            String result = wxReqUtils.querySendRequest(WxUrlConstant.GET_ACCESS_TOKEN_URL, pairList);
            return wxReqUtils.convertResponse(result, WxAccessTokenResp.class);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        LOGGER.error("Query access token error.");
        return null;
    }
}
