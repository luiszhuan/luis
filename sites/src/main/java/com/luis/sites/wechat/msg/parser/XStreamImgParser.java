package com.luis.sites.wechat.msg.parser;

import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.luis.sites.wechat.msg.entity.img.WxImgResponseMsgImgNode;
import com.luis.sites.wechat.msg.entity.img.WxImgRequestMsg;
import com.luis.sites.wechat.msg.entity.img.WxImgResponseMsg;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringEscapeUtils;

public class XStreamImgParser {
    private XStream xStream;

    private static XStreamImgParser instance = new XStreamImgParser();

    public static XStreamImgParser getInstance() {
        return instance;
    }

    private XStreamImgParser() {
        xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.ignoreUnknownElements();
        xStream.allowTypes(new Class[]{WxBaseRequestMsg.class, WxImgResponseMsgImgNode.class,
                WxImgRequestMsg.class, WxImgResponseMsg.class});
        //TODO 这个地方似乎只需要注解request
        xStream.processAnnotations(WxBaseRequestMsg.class);
        xStream.processAnnotations(WxImgResponseMsgImgNode.class);
        xStream.processAnnotations(WxImgRequestMsg.class);
        xStream.autodetectAnnotations(true);
    }

    public <T> String toXml(WxBaseRequestMsg input, Class<T> clazz) {
        return StringEscapeUtils.unescapeXml(xStream.toXML((T) input));
    }

    public <T> T fromXml(String originalMsg, Class<T> clazz) {
        return (T) xStream.fromXML(originalMsg);
    }

    /*public <T> T test(String orginalMsg, Class<T> clazz) {
        return (T)orginalMsg;
    }

    public static void main(String[] args) {
        XStreamImgParser xstreamParser=new XStreamImgParser();
        System.out.println(xstreamParser.test("liuzhuan",String.class));
    }*/
}
