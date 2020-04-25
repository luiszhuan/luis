package com.luis.webdemo.controller.download;

import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.*;

public class FileUtil {
    /**
     * 下载项目根目录下doc下的文件
     *
     * @param response response
     * @param fileName 文件名
     * @return 返回结果 成功或者文件不存在
     */
    public static String downloadFile(HttpServletResponse response, String fileName) {

        response.setHeader("content-type", "application/octet-stream");
        response.setContentType("application/octet-stream");
        try {
            response.setHeader("Content-Disposition", "attachment;filename=" + java.net.URLEncoder.encode(fileName, "UTF-8"));
        } catch (UnsupportedEncodingException e2) {
            e2.printStackTrace();
        }
        byte[] buff = new byte[1024];
        File path = null;
        try {
            path = new File(ResourceUtils.getURL("classpath:").getPath());
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        try (OutputStream os = response.getOutputStream();
             BufferedInputStream bis = new BufferedInputStream(new FileInputStream(new File(path + "/static/" + fileName)));) {
            int n = 0;
            while ((n = bis.read(buff)) != -1) {
                os.write(buff, 0, n);
                os.flush();
            }
        } catch (FileNotFoundException e1) {
            //e1.getMessage()+"系统找不到指定的文件";
            return "系统找不到指定的文件";
        } catch (IOException e) {
            e.printStackTrace();
        }
        return "success";
    }


}
