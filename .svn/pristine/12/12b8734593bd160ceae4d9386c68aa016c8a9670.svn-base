package com.business.scheduler.jobs.stat;

import com.business.core.constants.RedisConstants;
import com.business.core.entity.SchedulerValue;
import com.business.core.operations.schedulerValue.SchedulerValueCoreDao;
import com.business.core.operations.schedulerValue.SchedulerValueCoreService;
import com.business.core.utils.RedisUtil;
import com.business.scheduler.base.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by sin on 2015/10/14.
 */
@Service
public class WXAccessTokenJob extends BaseJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private SchedulerValueCoreService schedulerValueCoreService;
    @Autowired
    private SchedulerValueCoreDao schedulerValueCoreDao;

    /**
     * 将该定时器改为10分钟执行一次 TODO to Sin
     */
    @Scheduled(cron = "0 0/10 * * * ?")
    public void execute() {
        String token;
        String jsapiTicket;
        try {
            SchedulerValue oldSchedulerValue = schedulerValueCoreDao.findSchedulerValue();
            Long lastModifyTime = oldSchedulerValue.getLastModifyTime();
            if (lastModifyTime == null) {
                lastModifyTime = 0L;
            }

            if (System.currentTimeMillis() - lastModifyTime >= 1000 * 60 * 60 * 1
                    || oldSchedulerValue.getWxMqAccessToken().equals(oldSchedulerValue.getOldAccessToken())) {//一个小时 or 两次accessToken一样，导致的accessToken过期
                token = schedulerValueCoreService.getNewAccessToken();
                Update update = Update.update("wxMqAccessToken", token)
                        .set("lastModifyTime", System.currentTimeMillis())
                        .set("oldAccessToken", oldSchedulerValue.getWxMqAccessToken());
                schedulerValueCoreDao.schedulerValueUpsert(update);
            } else {//如果是最新的AccessToken，则每10分钟进行一次redis设置更新，避免redis中AccessToken未更新，导致微信步数设置失败
                token = oldSchedulerValue.getWxMqAccessToken();
            }

            RedisConstants.setWeChatAccessToken(token);

            {
                jsapiTicket = schedulerValueCoreService.getNewJsapiTicket(token);
                logger.error("jsapiTicket:" + jsapiTicket);
                schedulerValueCoreDao.schedulerValueUpsert(Update.update("jsapiTicket", jsapiTicket));
                RedisConstants.setJsapiTicket(jsapiTicket);
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                e.printStackTrace();
            }
            logger.info("get wx access_token error...");
        }
    }
}
