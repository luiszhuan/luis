package com.luis.sites.wechat.http.req.utils;

import com.luis.sites.wechat.access.token.schedule.QueryAccessTokenSchedule;
import com.luis.sites.wechat.constant.AppConstant;
import com.luis.sites.wechat.constant.WxMediaConstant;
import com.luis.sites.wechat.constant.WxUrlConstant;
import com.luis.sites.wechat.http.client.HttpClientPoolFactory;
import com.luis.sites.wechat.http.resp.media.WxMediaResp;
import org.apache.commons.io.IOUtils;
import org.apache.http.NameValuePair;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

@Component
public class WxMediaReqUtils {
    private static final Logger LOGGER = LogManager.getLogger(WxMediaReqUtils.class);

    @Autowired
    QueryAccessTokenSchedule queryAccessTokenSchedule;

    @Autowired
    WxReqUtils wxReqUtils;

    public WxMediaResp uploadTmpImg() {
        try {
            List<NameValuePair> pairList = new ArrayList<NameValuePair>();
            LOGGER.info("Access token " + queryAccessTokenSchedule.getWxAccessTokenResp().getAccessToken());
            BasicNameValuePair appId = new BasicNameValuePair(WxUrlConstant.URL_PARAM_TYPE, WxMediaConstant.MEDIA_TYPE_IMG);
            BasicNameValuePair appSecret = new BasicNameValuePair(WxUrlConstant.URL_PARAM_ACCESS_TOKEN, queryAccessTokenSchedule.getWxAccessTokenResp().getAccessToken());
            pairList.add(appId);
            pairList.add(appSecret);
            CloseableHttpClient client = HttpClientPoolFactory.getInstance().getIgnoreSslHttpClientPool();

            HttpPost httpPost = new HttpPost(wxReqUtils.replaceUrl(WxUrlConstant.MEDIA_UPLOAD_URL, pairList));
            String localFilePath = "cap.PNG";

            MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
            ContentType contentType = ContentType.create("multipart/form-data", AppConstant.UTF_8);
            multipartEntityBuilder.addBinaryBody("media", new File(localFilePath), contentType, "cap.PNG");
            multipartEntityBuilder.setCharset(Charset.forName(AppConstant.UTF_8));
            multipartEntityBuilder.setMode(HttpMultipartMode.BROWSER_COMPATIBLE);

            httpPost.setEntity(multipartEntityBuilder.build());
            httpPost.setHeader("Content-Type", contentType.toString());
            CloseableHttpResponse response = client.execute(httpPost);
            String result = IOUtils.toString(response.getEntity().getContent(), AppConstant.UTF_8);
            LOGGER.info("Query result: " + result);
            return wxReqUtils.convertResponse(result, WxMediaResp.class);
        } catch (Exception e) {
            LOGGER.error("", e);
        }
        LOGGER.error("Query access token error.");
        return null;
    }
}
