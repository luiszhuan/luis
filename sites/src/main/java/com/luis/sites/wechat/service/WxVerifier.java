package com.luis.sites.wechat.service;

import com.luis.sites.wechat.constant.AppConstant;
import com.luis.sites.wechat.param.WxParam;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.tomcat.util.buf.HexUtils;
import org.springframework.stereotype.Component;

import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Component
public class WxVerifier {
    private static final Logger logger = LogManager.getLogger(WxVerifier.class);

    private WxParam wxParam;

    public WxVerifier(WxParam wxParam) {
        this.wxParam = wxParam;
    }

    public String serverVerifyReplay(String timestamp, String nonce, String echostr, String signature) {
        logger.debug("WeChat verify request is signature={}, timestamp={}, nonce={}, echostr={}", signature, timestamp, nonce, echostr);

        String hashCode = getHashCode(timestamp, nonce, wxParam.getToken());
        boolean isSignCorrect = hashCode.equalsIgnoreCase(signature);

        logger.debug("Is from WeChat server ? {} = {} ,{}", signature, hashCode, isSignCorrect);
        return isSignCorrect ? echostr : "";
    }

    private String getHashCode(String timestamp, String nonce, String token) {
        //token, timestamp, nonce字典排序后sha1
        List<String> hashcodeList = new ArrayList<String>();
        hashcodeList.add(timestamp);
        hashcodeList.add(nonce);
        hashcodeList.add(token);
        Collections.sort(hashcodeList);
        String hashcode = "";
        try {
            MessageDigest sha1 = MessageDigest.getInstance("SHA1");
            for (String s : hashcodeList) {
                sha1.update(s.getBytes(AppConstant.UTF_8));
            }
            hashcode = HexUtils.toHexString(sha1.digest()).toLowerCase();
        } catch (NoSuchAlgorithmException | UnsupportedEncodingException e) {
            logger.error("", e);
        }
        return hashcode;
    }

}
