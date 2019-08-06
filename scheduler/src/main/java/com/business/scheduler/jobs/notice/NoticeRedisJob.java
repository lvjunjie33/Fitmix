package com.business.scheduler.jobs.notice;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.notice.Notice;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.util.Set;

/**
 * Created by Sin on 2016/4/26.
 *
 * notice 处理 redis ，同步到 mongo
 */
@Service
public class NoticeRedisJob extends BaseMongoDaoSupport {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0 23 * * ?")
    public void execute() {

        long now = System.currentTimeMillis();
        logger.info("notice 同步 mongo 开始...");

        Jedis jedis = RedisUtil.getResource(RedisConstants.CACHE_NOTICE_NEW_DB);
        Set<String> keys = jedis.keys("*");

        String[] delKey = new String[keys.size()];
        int index = 0;
        for (String key : keys) {
            String json = RedisUtil.cacheGet(RedisConstants.CACHE_NOTICE_NEW_DB, key);
            Notice noticeBase = JSON.parseObject(json, Notice.class);
            insertToMongo(noticeBase);

            delKey[index] = key;
            index ++;
        }
        logger.info("notice 同步 mongo 完成...清除 redis 缓存.");
        RedisUtil.del(RedisConstants.CACHE_NOTICE_NEW_DB, delKey);

        logger.info("notice 同步 mongo 结束...消耗 {} 毫秒!", System.currentTimeMillis() - now);
    }
}
