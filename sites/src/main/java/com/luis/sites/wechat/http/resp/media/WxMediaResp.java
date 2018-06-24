package com.luis.sites.wechat.http.resp.media;

import com.google.gson.annotations.SerializedName;
import com.luis.sites.wechat.http.resp.WxErrCodeResp;

/**
 * {"type":"TYPE","media_id":"MEDIA_ID","created_at":123456789}
 * {"errcode":40004,"errmsg":"invalid media type"}
 */
public class WxMediaResp extends WxErrCodeResp {

    @SerializedName("type")
    private String type;

    @SerializedName("media_id")
    private String mediaId;

    @SerializedName("created_at")
    private int createdAt;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMediaId() {
        return mediaId;
    }

    public void setMediaId(String mediaId) {
        this.mediaId = mediaId;
    }

    public int getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(int createdAt) {
        this.createdAt = createdAt;
    }

    @Override
    public String toString() {
        return "WxMediaResp{" +
                "type='" + type + '\'' +
                ", mediaId=" + mediaId +
                ", createdAt=" + createdAt +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

