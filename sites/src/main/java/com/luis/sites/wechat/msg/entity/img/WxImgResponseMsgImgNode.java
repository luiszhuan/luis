package com.luis.sites.wechat.msg.entity.img;

import com.luis.sites.wechat.msg.convertor.StringCDATAConvertor;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;

@XStreamAlias("Image")
public class WxImgResponseMsgImgNode {
    @XStreamAlias("MediaId")
    @XStreamConverter(StringCDATAConvertor.class)
    private String mediaId;

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    @Override
    public String toString() {
        return "ImgNode{" +
                "mediaId=" + mediaId +
                '}';
    }

}
