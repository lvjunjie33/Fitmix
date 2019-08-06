package com.business.msg.util.zip;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

/**
 * Created by edward on 2017/3/16.
 */
public class DownLoadFile {

    public static void  handle(String urlStr, String fileName,String savePath){
        //得到输入流
        InputStream inputStream = null;
        FileOutputStream fos = null;
        try {
            URL url = new URL(urlStr);
            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
            //设置超时间为20秒
            conn.setConnectTimeout(2 * 10000);
            //防止屏蔽程序抓取而返回403错误
            conn.setRequestProperty("User-Agent", "Mozilla/4.0 (compatible; MSIE 5.0; Windows NT; DigExt)");

            inputStream = conn.getInputStream();
            //获取自己数组
            byte[] getData = readInputStream(inputStream);

            //文件保存位置
            File saveDir = new File(savePath);
            if(!saveDir.exists()){
                saveDir.mkdir();
            }
            File file = new File(saveDir + File.separator + fileName);
            fos = new FileOutputStream(file);
            fos.write(getData);
            if(fos != null){
                fos.close();
            }
            if(inputStream != null){
                inputStream.close();
            }
        } catch (Exception e) {
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e1) {
                }
            }
            if (inputStream != null) {
                try {
                    inputStream.close();
                } catch (IOException e1) {
                }
            }
        }
    }

    public static byte[] getContent(String filePath) {
        byte[] buffer = null;
        FileInputStream fi = null;
        try {
            File file = new File(filePath);
            long fileSize = file.length();
            if (fileSize > Integer.MAX_VALUE) {
                return null;
            }
            fi = new FileInputStream(file);
            buffer = new byte[(int) fileSize];
            int offset = 0;
            int numRead = 0;
            while (offset < buffer.length
                    && (numRead = fi.read(buffer, offset, buffer.length - offset)) >= 0) {
                offset += numRead;
            }
            // 确保所有数据均被读取
            if (offset != buffer.length) {
                throw new IOException("Could not completely read file "
                        + file.getName());
            }
            fi.close();
        } catch (Exception e) {
            if (fi != null) {
                try {
                    fi.close();
                } catch (IOException e1) {
                }
            }
        }
        return buffer;
    }

    /**
     * 从输入流中获取字节数组
     * @param inputStream
     * @return
     * @throws IOException
     */
    public static byte[] readInputStream(InputStream inputStream) {
        ByteArrayOutputStream bos = null;
        try {
            byte[] buffer = new byte[1024 * 10];
            int len = 0;
            bos = new ByteArrayOutputStream();
            while((len = inputStream.read(buffer)) != -1) {
                bos.write(buffer, 0, len);
            }
            bos.close();
        } catch (Exception e) {
            if (bos != null) {
                try {
                    bos.close();
                } catch (IOException e1) {
                }
            }
        }
        return bos.toByteArray();
    }

}