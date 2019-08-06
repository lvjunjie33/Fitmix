package com.business.core.utils;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.language.CharTable;
import com.business.core.mongo.DefaultDao;
import com.business.core.mongo.RoutingMongoOperations;
import com.business.core.operations.language.CharTableCoreService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by sin on 2015/4/29.
 */
public class DicUtil {
    private static Logger LOGGER = LoggerFactory.getLogger(DicUtil.class);

    private static Map<Integer, List<Dictionary>> dictionaryMultimap;

    private static List<Dictionary> dictionaryList;

    public static synchronized void init () {
        long now = System.currentTimeMillis();
        LOGGER.info("初始化 Dictionary 开始...");
        //查询
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "type"));
        query.with(new Sort(Sort.Direction.ASC, "sort"));
        dictionaryList = BeanManager.getBean(DefaultDao.class).query(query, Dictionary.class, Dictionary.BASIC_INFO_FIELDS);
        if (!CollectionUtils.isEmpty(dictionaryList)) {
            dictionaryMultimap = CollectionUtil.buildMultimap(dictionaryList, Integer.class, Dictionary.class, "type");
        }
        // 构建 scene 路径
        List<Dictionary> dictionaries = new ArrayList<>();
        dictionaries.addAll(dictionaryMultimap.get(DicConstants.DIC_TYPE_MIX_SCENE));
        dictionaries.addAll(dictionaryMultimap.get(DicConstants.DIC_TYPE_RADIO_SCENE));
        for (Dictionary dic :  dictionaries) {
            if (null == dic.getOther()) {
                continue;
            }
            if (null == dic.getOther().get("image")) {
                continue;
            }

            Object url = dic.getOther().get("image");
            dic.getOther().put("image", FileCenterClient.buildUrl(url.toString()));
        }

        LOGGER.info("初始化 Dictionary 完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }

    /**
     * 获取所有 dictionary 信息
     * @return List 集合
     */
    public static List<Dictionary> allDictionary () {
        return dictionaryList;
    }

    public static List<Dictionary> allDictionary (String lan) {// English （en）
        if(Constants.LANGUAGE_EN.equals(lan)) {
            List<Dictionary> dis = new ArrayList<>();
            for (Dictionary d : dictionaryList) {
                try {
                    dis.add(d.clone());
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            }
            BeanManager.getBean(CharTableCoreService.class).dicsToEn(dis);
            return dis;
        }
        return dictionaryList;
    }

        /**
         * 获取 type 的 value List
         * @param type 类型
         * @return List<Dictionary> 集合
         */
    public static List<Dictionary> getDictionary(Integer type) {
        return dictionaryMultimap.get(type);
    }

    /**
     * 获取单个 dictionary
     * @param type 类型
     * @param value 值
     * @return 字典值
     */
    public static Dictionary getDictionary (Integer type, Integer value) {
        return CollectionUtil.buildMap(dictionaryMultimap.get(type), Integer.class, Dictionary.class, "value").get(value);
    }

    /**
     * 判断 value 是否存在
     * @param type 类型
     * @param value 值
     * @return true， false
     */
    public static boolean isDictionary (Integer type, Integer value) {
        List<Dictionary> dictionaryList = dictionaryMultimap.get(type);
        if (CollectionUtils.isEmpty(dictionaryList)) {
            return false;
        }
        Map<Integer, Dictionary> dictionaryMap = CollectionUtil.buildMap(dictionaryList, Integer.class, Dictionary.class, "value");
        return dictionaryMap.containsKey(value);
    }

    /**
     * 判断 value 是否存在
     * @param type 类型
     * @return true， false
     */
    public static boolean isDictionary (Integer type) {
        return !CollectionUtils.isEmpty(dictionaryMultimap.get(type));
    }
}
