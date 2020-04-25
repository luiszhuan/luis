package com.luis.sites.wechat.service;

import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.luis.sites.wechat.msg.parser.XStreamTxtParser;
import com.luis.sites.wechat.msg.processor.WxImgMsgProcessor;
import com.luis.sites.wechat.msg.processor.WxTxtMsgProcessor;
import com.luis.sites.wechat.msg.type.MsgType;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WxMsgDispatcher {
    private static final Logger logger = LogManager.getLogger(WxMsgDispatcher.class);
    private String originalMsg;
    private WxBaseRequestMsg wxBaseRequestMsg;

    public WxMsgDispatcher(String originalMsg) {
        this.originalMsg = originalMsg;
    }

    public String process() {
        logger.info("original msg = " + originalMsg);
        wxBaseRequestMsg = XStreamTxtParser.getInstance().fromXml(originalMsg, WxBaseRequestMsg.class);
        logger.info("wxBaseRequestMsg =" + wxBaseRequestMsg);
        if (MsgType.Text.toString().equals(wxBaseRequestMsg.getMsgType())) {
            return new WxTxtMsgProcessor(MsgType.Text, wxBaseRequestMsg, originalMsg).process();
        }
        if (MsgType.Image.toString().equals(wxBaseRequestMsg.getMsgType())) {
            return new WxImgMsgProcessor(MsgType.Image, wxBaseRequestMsg, originalMsg).process();
        }
        return "success";
    }
}
