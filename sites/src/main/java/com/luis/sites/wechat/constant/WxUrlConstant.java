package com.luis.sites.wechat.constant;

public class WxUrlConstant {
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=:{appId}&secret=:{appSecret}";
    public static final String URL_PARAM_APP_ID = "appId";
    public static final String URL_PARAM_APP_SECRET = "appSecret";
}
