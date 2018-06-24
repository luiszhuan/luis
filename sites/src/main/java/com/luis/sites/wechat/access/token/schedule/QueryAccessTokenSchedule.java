package com.luis.sites.wechat.access.token.schedule;

import com.luis.sites.wechat.http.req.utils.WxAccessTokenUtils;
import com.luis.sites.wechat.http.resp.access.token.WxAccessTokenResp;
import org.apache.commons.lang3.StringUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

@Component
public class QueryAccessTokenSchedule {
    private static final Logger LOGGER = LogManager.getLogger(QueryAccessTokenSchedule.class);
    private static final int MAX_RETRY_TIMES = 3;
    private static final int RETRY_DELAY = 5;
    private ScheduledExecutorService scheduledExecutorService;

    @Autowired
    private WxAccessTokenUtils wxAccessTokenUtils;

    private WxAccessTokenResp wxAccessTokenResp;

    public void start() {
        LOGGER.info("begin to start query access token schedule.");
        scheduledExecutorService = Executors.newScheduledThreadPool(1);
        //微信服务器间隔2小时更新一次token，这里间隔1小时45分更新一次token
        //每天获取token次数不能超过2000次
        scheduledExecutorService.scheduleAtFixedRate(new AccessTokenRunnable(0), 10, (60 + 45) * 60 * 60, TimeUnit.SECONDS);
        LOGGER.info("start query access token schedule success.");
    }

    public WxAccessTokenResp getWxAccessTokenResp() {
        return wxAccessTokenResp;
    }

    private class AccessTokenRunnable implements Runnable {

        private int retryTimes;

        AccessTokenRunnable(int retryTimes) {
            this.retryTimes = retryTimes;
        }

        @Override
        public void run() {
            try {
                WxAccessTokenResp newToken = wxAccessTokenUtils.queryAccessToken();
                if (!StringUtils.isEmpty(newToken.getAccessToken()) && !"null".equals(newToken.getAccessToken())) {
                    wxAccessTokenResp = newToken;
                    retryTimes = 0;
                    LOGGER.info("Get new access token success." + newToken);
                } else if (retryTimes++ < MAX_RETRY_TIMES) {
                    LOGGER.info("Get access error code {} msg {}, and retry {} later",
                            newToken.getErrCode(), newToken.getErrMsg(), RETRY_DELAY * retryTimes);
                    scheduledExecutorService.schedule(this, RETRY_DELAY * retryTimes, TimeUnit.SECONDS);
                } else {
                    LOGGER.error("Query access token error {} times. The task will be retry in next 1 hour and 45 minutes", retryTimes);
                    retryTimes = 0;
                }

            } catch (Throwable e) {
                LOGGER.error("", e);
            }
        }
    }
}
