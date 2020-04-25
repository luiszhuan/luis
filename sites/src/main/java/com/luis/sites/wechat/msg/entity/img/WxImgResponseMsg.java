package com.luis.sites.wechat.msg.entity.img;

import com.luis.sites.wechat.msg.convertor.StringCDATAConvertor;
import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

/**
 * <xml>
 * <ToUserName><![CDATA[粉丝号]]></ToUserName>
 * <FromUserName><![CDATA[公众号]]></FromUserName>
 * <CreateTime>1460536576</CreateTime>
 * <MsgType><![CDATA[image]]></MsgType>
 * <Image>
 * <MediaId><![CDATA[gyci5oxxxxxxv3cOL]]></MediaId>
 * </Image>
 * </xml>
 */
@XStreamAlias("xml")
public class WxImgResponseMsg extends WxBaseRequestMsg implements Serializable {

    @XStreamAlias("Image")
    WxImgResponseMsgImgNode imgNode;

    public WxImgResponseMsgImgNode getImgNode() {
        return imgNode;
    }

    public void setImgNode(WxImgResponseMsgImgNode imgNode) {
        this.imgNode = imgNode;
    }

    @Override
    public String toString() {
        return "WxImgResponseMsg{" +
                "imgNode=" + imgNode +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
