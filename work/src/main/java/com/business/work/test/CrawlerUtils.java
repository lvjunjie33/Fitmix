package com.business.work.test;

import com.alibaba.fastjson.JSON;
import com.business.core.utils.DateUtil;
import com.business.core.utils.HttpUtil;
import com.mongodb.DB;
import com.mongodb.DBCollection;
import com.mongodb.Mongo;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.StringEntity;
import org.apache.http.util.EntityUtils;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.zip.CRC32;

/**
 * Created by edward on 2016/4/22.
 * 抓一些网站的网页信息
 */
public class CrawlerUtils {

    public static void main(String[] args) throws IOException, InterruptedException {

        System.out.println("http:".indexOf("httsp:"));

        // Integer.parseInt("231806677");

        if(true) {
            return;
        }

        Mongo mongo = new Mongo("192.168.1.222", 27017);
        // 打开数据库test
        DB db = mongo.getDB("business");


        // 遍历所有集合的名字
        Set<String> colls = db.getCollectionNames();
        for (String s : colls) {
            System.out.println(s);
        }

        if (true) {
            return;
        }

//192.168.1.91:8068/club-message/add.json?version=0&lan=sm&_ch=appStore&_lan=ch&_nw=WiFi&_product=fitmix&_sdk=iPhone OS9.3.1&_v=6&uid=168310&clubId=223&content=测试

        final String urlA = "http://192.168.1.91:8068/t-user/jobA.htm";
        final String urlR = "http://192.168.1.91:8068/t-user/jobR.htm";
        final String urlS = "http://192.168.1.91:8068/t-user/jobS.htm";
        final String urlU = "http://192.168.1.91:8068/t-user/jobU.htm";
        final String urlIni = "http://192.168.1.91:8068/init.htm";

        final int num = 100;

        final int forNum = 100000;

        //Ini
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< num; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < forNum; j++){
                                Long bTime = System.currentTimeMillis();
                                Map<String, String> headers = new HashMap<>();
                                Map<String, Object> params = new HashMap<>();
                                String token = HttpUtil.request(urlIni, "post", params, headers, null);
                                Long eTime = System.currentTimeMillis();
                                System.out.println("初始化花费时间:" + (eTime - bTime) +"毫秒--------------响应信息:");
                            }
                        }
                    }).start();
                }
            }
        }).start();*/

        //A
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< num; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < forNum; j++){
                                Long bTime = System.currentTimeMillis();
                                Map<String, String> headers = new HashMap<>();
                                Map<String, Object> params = new HashMap<>();
                                String token = HttpUtil.request(urlA, "post", params, headers, null);
                                Long eTime = System.currentTimeMillis();
                                System.out.println("添加花费时间:" + (eTime - bTime) +"毫秒--------------响应信息:");
                            }
                        }
                    }).start();
                }
            }
        }).start();

        //S
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< num; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < forNum; j++) {
                                Long bTime = System.currentTimeMillis();
                                Map<String, String> headers = new HashMap<>();
                                Map<String, Object> params = new HashMap<>();
                                String token = HttpUtil.request(urlS, "post", params, headers, null);
                                Long eTime = System.currentTimeMillis();
                                System.out.println("查询花费时间:" + (eTime - bTime) + "毫秒--------------响应信息:" );
                            }
                        }
                    }).start();
                }
            }
        }).start();*/

        //U
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< num; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < forNum; j++) {
                                Long bTime = System.currentTimeMillis();
                                Map<String, String> headers = new HashMap<>();
                                Map<String, Object> params = new HashMap<>();
                                String token = HttpUtil.request(urlIni, "post", params, headers, null);
                                Long eTime = System.currentTimeMillis();
                                System.out.println("修改花费时间:" + (eTime - bTime) + "毫秒--------------响应信息:" );
                            }
                        }
                    }).start();
                }
            }
        }).start();*/

        //R
        /*new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i< num; i++) {
                    new Thread(new Runnable() {
                        @Override
                        public void run() {
                            for (int j = 0; j < forNum; j++) {
                                Long bTime = System.currentTimeMillis();
                                Map<String, String> headers = new HashMap<>();
                                Map<String, Object> params = new HashMap<>();
                                String token = HttpUtil.request(urlR, "post", params, headers, null);
                                Long eTime = System.currentTimeMillis();
                                System.out.println("删除花费时间:" + (eTime - bTime) + "毫秒--------------响应信息:" + token);
                            }
                        }
                    }).start();
                }
            }
        }).start();*/
    }
}
