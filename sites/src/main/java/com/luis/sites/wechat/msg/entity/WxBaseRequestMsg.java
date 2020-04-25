package com.luis.sites.wechat.msg.entity;

import com.luis.sites.wechat.msg.convertor.StringCDATAConvertor;
import com.thoughtworks.xstream.XStream;
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
 * </xml>
 */
@XStreamAlias("xml") //不需要，在子类中使用此注解
public class WxBaseRequestMsg implements Serializable {

    @XStreamAlias("ToUserName")
    @XStreamConverter(StringCDATAConvertor.class)
    protected String toUserName;

    @XStreamAlias("FromUserName")
    @XStreamConverter(StringCDATAConvertor.class)
    protected String fromUserName;

    @XStreamAlias("CreateTime")
    protected long createTime;

    @XStreamAlias("MsgType")
    @XStreamConverter(StringCDATAConvertor.class)
    protected String msgType;

    public String getToUserName() {
        return toUserName;
    }

    public void setToUserName(String toUserName) {
        this.toUserName = toUserName;
    }

    public String getFromUserName() {
        return fromUserName;
    }

    public void setFromUserName(String fromUserName) {
        this.fromUserName = fromUserName;
    }

    public long getCreateTime() {
        return createTime;
    }

    public void setCreateTime(long createTime) {
        this.createTime = createTime;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    /*public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }*/

    @Override
    public String toString() {
        return "WxBaseRequestMsg{" +
                "toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                //", content='" + content + '\'' +
                '}';
    }

    /*public static void main(String[] args) {
        String msg = "<xml> <ToUserName><![CDATA[fans]]></ToUserName> <FromUserName><![CDATA[public]]></FromUserName> <CreateTime>1460541339</CreateTime> <MsgType><![CDATA[text]]></MsgType> <Content><![CDATA[test]]></Content> </xml>\n";
        XStream xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.allowTypes(new Class[]{WxBaseRequestMsg.class, WxTxtResponseMsg.class});
        //xStream.ignoreUnknownElements();
        xStream.processAnnotations(WxBaseRequestMsg.class);
        xStream.autodetectAnnotations(true);
        WxBaseRequestMsg input = (WxBaseRequestMsg) xStream.fromXML(msg);
        System.out.println(xStream.fromXML(msg));
        System.out.println(input);
    }*/
}
