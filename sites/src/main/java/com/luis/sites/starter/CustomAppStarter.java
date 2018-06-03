package com.luis.sites.starter;

import com.luis.sites.wechat.acess.token.schedule.QueryAccessTokenSchedule;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * spring boot提供两种“开机任务”方法：
 * ApplicationRunner和CommandLineRunner
 * 这里使用applicationRunner
 */
@Component
@Order(1)
public class CustomAppStarter implements ApplicationRunner {
    private static final Logger LOGGER = LogManager.getLogger(CustomAppStarter.class);

    @Autowired
    private QueryAccessTokenSchedule queryAccessTokenSchedule;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        LOGGER.info("start with spring boot");
        queryAccessTokenSchedule.start();
    }
}
