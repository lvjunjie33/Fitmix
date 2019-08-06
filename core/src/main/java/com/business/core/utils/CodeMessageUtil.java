package com.business.core.utils;


import com.business.core.constants.Constants;
import com.business.core.entity.Dictionary;
import com.business.core.entity.code.CodeMessage;
import com.business.core.entity.language.CharTable;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.mongo.DefaultDao;
import com.business.core.mongo.RoutingMongoOperations;
import com.business.core.operations.language.CharTableCoreService;
import com.google.common.collect.ImmutableMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by sin on 2015/11/23.
 * error code 错误码
 */
public class CodeMessageUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(CodeMessageUtil.class);

    private static Map<Integer, CodeMessage> messageMap = ImmutableMap.<Integer, CodeMessage>builder().build();

    /**
     * 初始化 app code message
     */
    public static void init(String sys) {
        long now = System.currentTimeMillis();
        LOGGER.info("初始化CodeMessageUtil开始...");

        ImmutableMap.Builder<Integer, CodeMessage> builder = ImmutableMap.builder();
        Query query = new Query(Criteria.where("sys").is(sys));
        query.fields().include("code").include("msgChina").include("msgEnglish");
        List<CodeMessage> codeMessageList = BeanManager.getBean(RoutingMongoOperations.class).find(query, CodeMessage.class);

        for (CodeMessage codeMessage : codeMessageList) {
            builder.put(codeMessage.getCode(), codeMessage);
        }
        messageMap = builder.build();
        LOGGER.info("初始化CodeMessageUtil完成...消耗：{}毫秒!", System.currentTimeMillis() - now);
    }

    /**
     * 格式化 错误信息
     * @param code 错误码
     * @param language 语言
     * @param params 格式化参数
     * @return 格式化内容
     */
    public static String format(int code, String language, Object...params) {
        CodeMessage codeMessage = messageMap.get(code);
        if (codeMessage != null) {
            if (Constants.LANGUAGE_EN.equals(language)) {
                try {
                    codeMessage = codeMessage.clone();
                    BeanManager.getBean(CharTableCoreService.class).codeMessageToEn(codeMessage);
                } catch (CloneNotSupportedException e) {
                    e.printStackTrace();
                }
            } else {
                codeMessage = messageMap.get(code);
            }
        } else{
            LOGGER.error("[format][错误码({})没有对应的错误消息配置", code);
            return "";
        }
        return doFormat(code, codeMessage.getMsgChina(), params);
    }

    /**
     * 将错误编号对应的消息使用params进行格式化。
     *
     * @param code           错误编号
     * @param messagePattern 消息模版
     * @param params         参数
     * @return 格式化后的提示
     */
    private static String doFormat(int code, String messagePattern, Object... params) {
        StringBuilder builder = new StringBuilder(messagePattern.length() + 50);
        int i = 0;
        int j;
        int l;
        for (l = 0; l < params.length; l++) {
            j = messagePattern.indexOf("{}", i);
            if (j == -1) {
                LOGGER.error("[doFormat][参数过多：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
                if (i == 0) {
                    return messagePattern;
                } else {
                    builder.append(messagePattern.substring(i, messagePattern.length()));
                    return builder.toString();
                }
            } else {
                builder.append(messagePattern.substring(i, j));
                builder.append(params[l]);
                i = j + 2;
            }
        }
        if (messagePattern.indexOf("{}", i) != -1) {
            LOGGER.error("[doFormat][参数过少：错误码({})|错误内容({})|参数({})", code, messagePattern, params);
        }
        builder.append(messagePattern.substring(i, messagePattern.length()));
        return builder.toString();
    }
}
