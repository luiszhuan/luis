package com.luis.sites.wechat.msg.entity.txt;

import com.luis.sites.wechat.msg.convertor.StringCDATAConvertor;
import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

@XStreamAlias("xml")
public class WxTxtResponseMsg extends WxBaseRequestMsg implements Serializable {

    @XStreamAlias("Content")
    @XStreamConverter(StringCDATAConvertor.class)
    private String content = "text";

    public String getContent() {
        return content;
    }

    public void setContent(String context) {
        this.content = context;
    }

    @Override
    public String toString() {
        return "WxTxtResponseMsg{" +
                super.toString() + ", " +
                "content='" + content + '\'' +
                '}';
    }
}
