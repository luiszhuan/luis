package com.luis.sites.wechat.http.resp;

import com.google.gson.annotations.SerializedName;

public class WxErrCodeResp {

    @SerializedName("errcode")
    protected int errCode;

    @SerializedName("errmsg")
    protected String errMsg;

    public int getErrCode() {
        return this.errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return this.errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }

    @Override
    public String toString() {
        return "WxAccessTokenResp{" +
                ", errCode=" + errCode +
                ", errMsg='" + errMsg + '\'' +
                '}';
    }
}

