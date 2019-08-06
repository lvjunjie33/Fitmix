package com.business.core.utils;

import com.business.core.constants.ApplicationConfig;
import com.sun.org.apache.xpath.internal.operations.Bool;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

/**
 * Created by sin on 2015/8/28.
 * 刷新 app stat缓存
 *  {@link com.business.core.entity.SysParam} 系统参数缓存
 *  {@link com.business.core.entity.Dictionary} 字典缓存
 */
public class StaticCacheUtil {

    private static Logger logger = LoggerFactory.getLogger(StaticCacheUtil.class);

    /**
     * 刷新 系统参数缓存
     * @return true(成功)， false(失败)
     */
    public static Boolean refreshSysParam() {
        logger.info("Refresh SysParam 开始...");
        Long now = System.currentTimeMillis();
        Boolean result = refreshCache(ApplicationConfig.APP_SYS_PARAM_URL);
        logger.info("Refresh SysParam 结束...消耗 {}毫秒", System.currentTimeMillis() - now);
        return result;
    }

    /**
     * 刷新 字典
     * @return true(成功)， false(失败)
     */
    public static Boolean refreshDictionary() {
        logger.info("Refresh Dictionary 开始...");
        Long now = System.currentTimeMillis();
        Boolean result = refreshCache(ApplicationConfig.APP_DIC_URL);
        logger.info("Refresh Dictionary 结束...消耗 {}毫秒", System.currentTimeMillis() - now);
        return result;
    }

    public static Boolean refreshCache(String url) {
        CloseableHttpResponse response = null;
        try {
            HttpGet httpGet = new HttpGet(url);
            response = HttpUtil.getHttpClient().execute(httpGet);
            if (response.getStatusLine().getStatusCode() != 200) {
                return false;
            }
        } catch (IOException e) {
            logger.debug("Refresh Cache 失败...");
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
        } finally {
            HttpUtil.closeQuietly(response);
        }
        return true;
    }
}
