package com.luis.sites.wechat.msg.entity.txt;

import com.luis.sites.wechat.msg.convertor.StringCDATAConvertor;
import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

/**
 * <xml>
 * <ToUserName><![CDATA[粉丝号]]></ToUserName>
 * <FromUserName><![CDATA[公众号]]></FromUserName>
 * <CreateTime>1460541339</CreateTime>
 * <MsgType><![CDATA[text]]></MsgType>
 * <Content><![CDATA[test]]></Content>
 * <MsgId>1234567890123456</MsgId>
 * </xml>
 */
@XStreamAlias("xml")
public class WxTxtRequestMsg extends WxBaseRequestMsg implements Serializable {

    @XStreamAlias("Content")
    @XStreamConverter(StringCDATAConvertor.class)
    private String content;

    @XStreamAlias("MsgId")
    private long msgId;


    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    @Override
    public String toString() {
        return "WxTxtRequestMsg{" +
                super.toString() + ", " +
                "content='" + content + '\'' +
                ", msgId=" + msgId +
                '}';
    }
}
