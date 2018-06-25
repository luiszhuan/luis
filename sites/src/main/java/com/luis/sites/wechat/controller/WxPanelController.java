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
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;

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

        if (file.isEmpty()) {
            return "文件为空";
        }
        // 获取文件名
        String fileName = file.getOriginalFilename();
        LOGGER.info("Upload file name is " + fileName);
        // 获取文件的后缀名
        String suffixName = fileName.substring(fileName.lastIndexOf("."));
        LOGGER.info("suffix name is " + suffixName);
        // 文件上传后的路径
        String filePath = "tmp/";
        // 解决中文问题，liunx下中文路径，图片显示问题
        // fileName = UUID.randomUUID() + suffixName;
        File dest = new File(filePath + fileName);
        // 检测是否存在目录
        if (!dest.getParentFile().exists()) {
            dest.getParentFile().mkdirs();
        }
        try {
            file.transferTo(dest.getCanonicalFile());
            LOGGER.info("Save file to " + dest.getCanonicalPath());

        } catch (IllegalStateException | IOException e) {
            LOGGER.error("", e);
        }

        WxMediaResp result = wxMediaReqUtils.uploadTmpImg(filePath, fileName);
        LOGGER.info("upload successful" + result);
        LOGGER.info(file.getSize());
        return "redirect:/wx/panel";
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
