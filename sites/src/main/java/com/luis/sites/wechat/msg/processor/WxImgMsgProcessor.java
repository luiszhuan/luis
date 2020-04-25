package com.luis.sites.wechat.msg.processor;

import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.luis.sites.wechat.msg.entity.img.WxImgRequestMsg;
import com.luis.sites.wechat.msg.entity.img.WxImgResponseMsg;
import com.luis.sites.wechat.msg.entity.img.WxImgResponseMsgImgNode;
import com.luis.sites.wechat.msg.entity.txt.WxTxtResponseMsg;
import com.luis.sites.wechat.msg.parser.XStreamImgParser;
import com.luis.sites.wechat.msg.type.MsgType;
import org.apache.commons.lang3.StringEscapeUtils;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class WxImgMsgProcessor extends WxMsgProcessor {
    private static final Logger logger = LogManager.getLogger(WxImgMsgProcessor.class);
    private WxImgRequestMsg input;

    public WxImgMsgProcessor(MsgType msgType, WxBaseRequestMsg requestMsg, String originalMsg) {
        super(msgType, requestMsg, originalMsg);
        input = XStreamImgParser.getInstance().fromXml(originalMsg, WxImgRequestMsg.class);
        logger.info("request msg  is :" + input);
    }

    @Override
    public String process() {
        WxImgResponseMsg imgMsg = new WxImgResponseMsg();
        imgMsg.setCreateTime(System.currentTimeMillis() / 1000);
        imgMsg.setMsgType(this.msgType.toString());
        WxImgResponseMsgImgNode imgeNode = new WxImgResponseMsgImgNode();
        imgeNode.setMediaId(input.getMediaId());
        imgMsg.setImgNode(imgeNode);
        imgMsg.setFromUserName(input.getToUserName());
        imgMsg.setToUserName(input.getFromUserName());
        String msgXml = StringEscapeUtils.unescapeXml(XStreamImgParser.getInstance().toXml(imgMsg, WxTxtResponseMsg.class));
        logger.info("response is :" + msgXml);
        return msgXml;
    }
}
