package com.luis.sites.wechat.constant;

public class WxUrlConstant {
    public static final String GET_ACCESS_TOKEN_URL = "https://api.weixin.qq.com/cgi-bin/token?" +
            "grant_type=client_credential&appid=:{appId}&secret=:{appSecret}";
    public static final String URL_PARAM_APP_ID = "appId";
    public static final String URL_PARAM_APP_SECRET = "appSecret";
    /**
     * 上传资源 多媒体文件上传接口 /media/upload
     */
    public static final String MEDIA_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=:{accessToken}&type=:{type}";
    public static final String URL_PARAM_ACCESS_TOKEN = "accessToken";
    public static final String URL_PARAM_TYPE = "type";

    //获取临时资源
    public static final String MEDIA_GET_URL = "https://api.weixin.qq.com/cgi-bin/media/get?access_token=:{accessToken}&media_id={mediaId}";
    public static final String URL_PARAM_MEDIA_ID = "mediaId";
    /**
     * 上传logo接口 /media/uploadimg
     */
    public static final String LOG_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/uploadimg?access_token=:{accessToken}";
    /**
     * 永久资源
     * 多媒体文件上传接口 /media/add_news
     * http请求方式：POST/FORM，使用https
     * https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE
     * 调用示例（使用curl命令，用FORM表单方式上传一个多媒体文件）：
     * curl -F media=@test.jpg "https://api.weixin.qq.com/cgi-bin/media/upload?access_token=ACCESS_TOKEN&type=TYPE"
     */
    public static final String MEDIA_PERMANENT_UPLOAD_URL = "https://api.weixin.qq.com/cgi-bin/media/add_news?access_token=:{accessToken}&type=:{type}";


}
