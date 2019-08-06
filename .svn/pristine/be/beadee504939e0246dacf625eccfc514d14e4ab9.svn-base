package com.business.core.utils;

import org.apache.commons.httpclient.util.URIUtil;
import org.apache.http.client.utils.URLEncodedUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.URL;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by fenxio on 2016/7/26.
 */
public class MongoUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(MongoUtil.class);

    /**
     * 转义map key
     * @param map
     * @return
     */
    public static HashMap<Object, Object> enConverterMap(Map<Object, Object> map) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        try {
            if(null != map && map.size() > 0) {
                for(Object key : map.keySet()) {
                    resultMap.put(URIUtil.encodeAll((String) key), map.get(key));
                }
            }
        } catch (Exception e) {
            LOGGER.error(String.format("encodeAll map 错误 ：%s", e.getMessage()));
        }

        return resultMap;
    }

    /**
     * 反转义map key
     * @param map
     * @return
     */
    public static HashMap<Object, Object> deConverterMap(Map<Object, Object> map) {
        HashMap<Object, Object> resultMap = new HashMap<>();
        try {
            if(null != map && map.size() > 0) {
                for(Object key : map.keySet()) {
                    resultMap.put(URIUtil.decode((String) key), map.get(key));
                }
            }
        } catch (Exception e) {
            LOGGER.error(String.format("decode map 错误 ：%s", e.getMessage()));
        }
        return resultMap;
    }

}
