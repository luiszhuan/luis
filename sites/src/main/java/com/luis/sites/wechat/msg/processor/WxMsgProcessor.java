package com.luis.sites.wechat.msg.processor;

import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.luis.sites.wechat.msg.type.MsgType;

public abstract class WxMsgProcessor {

    protected MsgType msgType;
    WxBaseRequestMsg requestMsg;
    private String originalMsg;

    public WxMsgProcessor(MsgType msgType, WxBaseRequestMsg requestMsg, String originalMsg) {
        this.msgType = msgType;
        this.requestMsg = requestMsg;
        this.originalMsg = originalMsg;
    }

    public abstract String process();
}
