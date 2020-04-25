package com.luis.sites.wechat.msg.processor;

import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.luis.sites.wechat.msg.entity.txt.WxTxtRequestMsg;
import com.luis.sites.wechat.msg.entity.txt.WxTxtResponseMsg;
import com.luis.sites.wechat.msg.parser.XStreamImgParser;
import com.luis.sites.wechat.msg.type.MsgType;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WxTxtMsgProcessor extends WxMsgProcessor {

    private static final Logger logger = LogManager.getLogger(WxTxtMsgProcessor.class);
    private WxTxtRequestMsg input;

    public WxTxtMsgProcessor(MsgType msgType, WxBaseRequestMsg requestMsg, String originalMsg) {
        super(msgType, requestMsg, originalMsg);
        input = (WxTxtRequestMsg) requestMsg;
        logger.info("request msg  is :" + input);
    }

    @Override
    public String process() {
        WxTxtResponseMsg txtMsg = new WxTxtResponseMsg();
        txtMsg.setCreateTime(System.currentTimeMillis() / 1000);
        txtMsg.setMsgType(this.msgType.toString());
        txtMsg.setContent("Hell, " + input.getContent());
        txtMsg.setFromUserName(input.getToUserName());
        txtMsg.setToUserName(input.getFromUserName());
        String msgXml = StringEscapeUtils.unescapeXml(XStreamImgParser.getInstance().toXml(
                txtMsg, WxTxtResponseMsg.class));
        logger.info("response is :" + msgXml);
        return msgXml;
    }
}
