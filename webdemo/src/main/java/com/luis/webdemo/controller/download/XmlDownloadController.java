package com.luis.webdemo.controller.download;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletResponse;

@Controller
@RequestMapping(value = "/download/")
public class XmlDownloadController {

    @ResponseBody
    @RequestMapping("xml")
    public void xmlDownload(HttpServletResponse response) {
        String fileName = "demo.xml";
        String result = FileUtil.downloadFile(response, fileName);
    }
}
