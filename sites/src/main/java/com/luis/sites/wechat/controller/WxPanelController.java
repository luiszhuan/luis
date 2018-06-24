package com.luis.sites.wechat.controller;


import com.luis.sites.wechat.access.token.schedule.QueryAccessTokenSchedule;
import com.luis.sites.wechat.http.req.utils.WxMediaReqUtils;
import com.luis.sites.wechat.http.resp.media.WxMediaResp;
import com.luis.sites.wechat.param.WxParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@RequestMapping("/wx/")
public class WxPanelController {
    private static final Logger LOGGER = LogManager.getLogger(WxPanelController.class);

    @Autowired
    WxMediaReqUtils wxMediaReqUtils;

    @Autowired
    WxParam wxParam;

    @Autowired
    QueryAccessTokenSchedule accessTokenSchedule;


    @RequestMapping("panel")
    public String weChatTest() {
        return "wx/panel";
    }

    @RequestMapping("upload")
    public String uploadTest(@RequestParam("file") MultipartFile file) {
        LOGGER.info("Upload image test");
        WxMediaResp result = wxMediaReqUtils.uploadTmpImg();
        LOGGER.info("upload successful" + result);
        LOGGER.info(file.getSize());
        return "wx/panel";
    }

    @ModelAttribute("wxParam")
    public WxParam GetWxParam() {
        return wxParam;
    }

    @ModelAttribute("accessToken")
    public String GetAccessToken() {
        return accessTokenSchedule.getWxAccessTokenResp().getAccessToken();
    }
}
