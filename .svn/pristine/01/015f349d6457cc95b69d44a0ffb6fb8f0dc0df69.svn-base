package com.business.scheduler.jobs.activity;

import com.business.core.entity.activity.Activity;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

/**
 * Created by edward on 2016/9/22.
 */
@Service
public class ActivityJob extends BaseMongoDaoSupport {

    /**
     * 第三方赛事结束，sort设置为0,依据活动开始时间进行排序，在赛事列表最后展示
     */
    public static final int DEFAULT_END_EVENT_SORT = -1;

    /**
     * 每天凌晨1点执行 TODO to Sin
     */
    @Scheduled(cron = "0 0 1 * * ?")
    public void execute() {
        {//第三方赛事,结束相关业务处理
            Query query = Query.query(Criteria.where("type").is(Activity.TYPE_OUTER).and("activityEndTime").lte(System.currentTimeMillis()));
            DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
            defaultDao.modifyMore(query, Update.update("sort", DEFAULT_END_EVENT_SORT), Activity.class);
        }
    }
}
