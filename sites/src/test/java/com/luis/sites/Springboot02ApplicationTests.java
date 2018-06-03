package com.luis.sites;

import com.luis.sites.wechat.param.WxParam;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class Springboot02ApplicationTests {

    @Autowired
    WxParam wxParam;
    @Test
    public void contextLoads() {
        //System.out.println(person);
        System.out.println(wxParam.getToken());
    }
}
