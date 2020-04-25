package com.luis.sites.wechat.msg.entity;

import com.luis.sites.wechat.msg.entity.txt.WxTxtResponseMsg;
import com.thoughtworks.xstream.XStream;
import org.apache.commons.lang3.StringEscapeUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class WxTxtResponseMsgTest {

    String msg = "<xml>" +
            "<ToUserName><![CDATA[toUser]]></ToUserName>" +
            "<FromUserName><![CDATA[fromUser]]></FromUserName>" +
            "<CreateTime>1348831860</CreateTime>" +
            "<MsgType><![CDATA[text]]></MsgType>" +
            "<Content><![CDATA[this is a test]]></Content>" +
            //"<MsgId>1234567890123456</MsgId>" +
            "</xml>";
    XStream xStream;
    WxTxtResponseMsg outputMessage;

    @Before
    public void setUp() {
        xStream = new XStream();
        XStream.setupDefaultSecurity(xStream);
        xStream.ignoreUnknownElements();
        xStream.allowTypes(new Class[]{WxBaseRequestMsg.class, WxTxtResponseMsg.class});
        xStream.ignoreUnknownElements();
        xStream.processAnnotations(WxBaseRequestMsg.class);
        xStream.processAnnotations(WxTxtResponseMsg.class);
        xStream.autodetectAnnotations(true);
    }

    @Test
    public void fromXmlTest() {
        outputMessage = (WxTxtResponseMsg) xStream.fromXML(msg);
        Assert.assertTrue("from xml error", outputMessage != null);
    }

    @Test
    public void toXmlTest() {
        fromXmlTest();
        Assert.assertEquals("Msg not equal",
                msg.replaceAll("\\s", ""),
                StringEscapeUtils.unescapeXml(xStream.toXML(outputMessage)).replaceAll("\\s", ""));
    }
}
