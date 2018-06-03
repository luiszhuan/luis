package com.luis.sites.wechat.controller;

import com.luis.sites.wechat.param.WxParam;
import com.luis.sites.wechat.service.WxMsgDispatcher;
import com.luis.sites.wechat.service.WxVerifier;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/wechat")
public class WxController {
    private static final Logger logger = LogManager.getLogger(WxController.class);

    @Autowired
    private WxParam wxParam;

    /**
     * 微信校验服务是否正确
     * https://mp.weixin.qq.com/debug/cgi-bin/sandboxinfo?action=showinfo&t=sandbox/index
     *
     * @param signature
     * @param timestamp
     * @param nonce
     * @param echostr
     * @return 签名正确返回nonce，否则""
     */
    @GetMapping()
    public String serverVerify(@RequestParam(name = "signature") String signature,
                               @RequestParam(name = "timestamp") String timestamp,
                               @RequestParam(name = "nonce") String nonce,
                               @RequestParam(name = "echostr") String echostr) {
        logger.debug("Receive get request.");
        return new WxVerifier(wxParam).serverVerifyReplay(timestamp, nonce, echostr, signature);
    }

    @PostMapping()
    public String processMsg(@RequestBody String originalMsg) {
        logger.debug("Receive post request.");
        String responseMsg = new WxMsgDispatcher(originalMsg).process();
        return responseMsg;
    }
}
