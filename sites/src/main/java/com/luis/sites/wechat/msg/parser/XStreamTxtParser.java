package com.luis.sites.wechat.msg.parser;

import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.luis.sites.wechat.msg.entity.txt.WxTxtRequestMsg;
import com.luis.sites.wechat.msg.entity.txt.WxTxtResponseMsg;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringEscapeUtils;

public class XStreamTxtParser {
    private XStream xStream;

    private static XStreamTxtParser instance = new XStreamTxtParser();

    public static XStreamTxtParser getInstance() {
        return instance;
    }

    private XStreamTxtParser() {
        xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.ignoreUnknownElements();
        xStream.allowTypes(new Class[]{WxBaseRequestMsg.class, WxTxtRequestMsg.class, WxTxtResponseMsg.class});
        //TODO 这个地方似乎只需要注解request
        xStream.processAnnotations(WxBaseRequestMsg.class);
        xStream.processAnnotations(WxTxtRequestMsg.class);
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
