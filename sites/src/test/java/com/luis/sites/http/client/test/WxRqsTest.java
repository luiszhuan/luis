package com.luis.sites.http.client.test;


import com.luis.sites.wechat.constant.AppConstant;
import com.luis.sites.wechat.constant.WxUrlConstant;
import com.luis.sites.wechat.http.client.HttpClientFactory;
import com.luis.sites.wechat.http.request.utils.WxAccessTokenResp;
import com.luis.sites.wechat.http.request.utils.WxReqsUtils;
import com.luis.sites.wechat.param.WxParam;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WxRqsTest {

    @Autowired
    WxParam wxParam;

    @Autowired
    WxReqsUtils wxReqsUtils;

    @Test
    public void replaceTest() {
        String result = wxAccessTokenUrl();
        Assert.assertTrue(result != null);
    }

    //@Test
    public void poolingHttpClientTest() throws Exception {
        CloseableHttpClient client = HttpClientFactory.getInstance().getIgnoreSSLClient();
        HttpGet httpGet = new HttpGet(wxAccessTokenUrl());
        CloseableHttpResponse response = client.execute(httpGet);
        String result = IOUtils.toString(response.getEntity().getContent(), AppConstant.UTF_8);
        WxAccessTokenResp wxAccessTokenResp = wxReqsUtils.convertResponse(result);
        Assert.assertTrue(wxAccessTokenResp != null);
    }

    @Test
    public void parseWxAccessTokenRsp() {
        String responseStr = "{\"access_token\":" +
                "\"10_c19JU9_8T19MLAAbBEhJhZ3nztexBMs8hXwNwMq0ePPzz25ReXpZMHV-VpL5Mvxhhg6QqM9X7_WGFUZ0CJDH6L5_5ZNVwqR4Op8BZyoGZNtdZp9jmZZSfQDOpFsy__nfUUuJOztDkAUSakebADQaADAEHU\"," +
                "\"expires_in\":7200}";
        String errResponseStr = "{\"errcode\":40013,\n" + "\"errmsg\":\"invalid appid\"\n" + "}";
        WxAccessTokenResp wxAccessTokenResp = wxReqsUtils.convertResponse(responseStr);
        Assert.assertTrue(wxAccessTokenResp.getAccessToken() != null);
        Assert.assertTrue(wxAccessTokenResp.getExpiresIn() == 7200);
        Assert.assertTrue(wxAccessTokenResp.getErrCode() == 0);
        Assert.assertTrue(wxAccessTokenResp.getErrMsg() == null);
        wxAccessTokenResp = wxReqsUtils.convertResponse(errResponseStr);
        Assert.assertTrue(wxAccessTokenResp.getAccessToken() == null);
        Assert.assertTrue(wxAccessTokenResp.getExpiresIn() == 0);
        Assert.assertTrue(wxAccessTokenResp.getErrCode() == 40013);
        Assert.assertTrue(wxAccessTokenResp.getAccessToken() == null);
    }

    private String wxAccessTokenUrl() {
        List<NameValuePair> pairList = new ArrayList<NameValuePair>();
        BasicNameValuePair appId = new BasicNameValuePair("appId", wxParam.getAppId());
        BasicNameValuePair appSecret = new BasicNameValuePair("appSecret", wxParam.getAppSecret());
        pairList.add(appId);
        pairList.add(appSecret);
        return wxReqsUtils.replaceUrl(WxUrlConstant.GET_ACCESS_TOKEN_URL, pairList);
    }
}
