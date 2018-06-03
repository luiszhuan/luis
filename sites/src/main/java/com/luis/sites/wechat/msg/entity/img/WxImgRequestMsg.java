package com.luis.sites.wechat.msg.entity.img;

import com.luis.sites.wechat.msg.convertor.StringCDATAConvertor;
import com.luis.sites.wechat.msg.entity.WxBaseRequestMsg;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

import java.io.Serializable;

/**
 * <xml>
 * <ToUserName>< ![CDATA[toUser] ]></ToUserName>
 * <FromUserName>< ![CDATA[fromUser] ]></FromUserName>
 * <CreateTime>1348831860</CreateTime>
 * <MsgType>< ![CDATA[image] ]></MsgType>
 * <PicUrl>< ![CDATA[this is a url] ]></PicUrl>
 * <MediaId>< ![CDATA[media_id] ]></MediaId> <MsgId>1234567890123456</MsgId>
 * </xml>
 */
@XStreamAlias("xml")
public class WxImgRequestMsg extends WxBaseRequestMsg implements Serializable {

    @XStreamAlias("PicUrl")
    @XStreamConverter(StringCDATAConvertor.class)
    private String picUrl;

    @XStreamAlias("MsgId")
    private long msgId;

    @XStreamAlias("MediaId")
    @XStreamConverter(StringCDATAConvertor.class)
    private String mediaId;

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public long getMsgId() {
        return msgId;
    }

    public void setMsgId(long msgId) {
        this.msgId = msgId;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "WxImgRequestMsg{" +
                "picUrl='" + picUrl + '\'' +
                ", msgId=" + msgId +
                ", mediaId='" + mediaId + '\'' +
                ", toUserName='" + toUserName + '\'' +
                ", fromUserName='" + fromUserName + '\'' +
                ", createTime=" + createTime +
                ", msgType='" + msgType + '\'' +
                '}';
    }
}
