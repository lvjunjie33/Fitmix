package com.business.scheduler.jobs.stat;

import com.business.core.entity.stat.UserExperienceAndUserStat;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserExperience;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.userExperience.UserExperienceCoreDao;
import com.business.core.utils.DateUtil;
import com.business.scheduler.base.BaseJob;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by sin on 2015/9/8.
 */
@Service
public class UserExperienceAndUserJob extends BaseJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserExperienceCoreDao experienceCoreDao;

    /**
     * 每天 0 (24)点执行
     */
    @Override
    public void execute() {
        logger.info("job 开始 ...");
        Date date = DateUtil.addDate(Calendar.DAY_OF_YEAR, -1);
        Long now = System.currentTimeMillis();
        home(DateUtil.getDayBegin(date).getTime(), DateUtil.getDayEnd(date).getTime());
        logger.info("job 结束 ...消耗 {} 毫秒", System.currentTimeMillis() - now);
    }

    public void home(Long beginTime, Long endTime) {
        String addTimeStr = DateUtil.format(DateUtil.parse(endTime), "yyyy-MM-dd");
        if (!CollectionUtils.isEmpty(getRoutingMongoOps().find(Query.query(Criteria.where("addTimeStr").is(addTimeStr)), UserExperienceAndUserStat.class))) {
            logger.info("job 终止统计, 该天已存在 {}", addTimeStr);
            return;
        }
        /// 今天注册用户
        List<User> toDayUserList = userCoreDao.findUserByAddTime(beginTime, endTime);
        /// 今天体验用户
        List<UserExperience> toDayUserExperienceList = experienceCoreDao.findUserExperienceByAddTime(beginTime, endTime);

        UserExperienceAndUserStat userExperienceAndUserStat = new UserExperienceAndUserStat();
        userExperienceAndUserStat.setTotalCount(toDayUserList.size() + toDayUserExperienceList.size());
        userExperienceAndUserStat.setExperienceCount(toDayUserExperienceList.size());
        userExperienceAndUserStat.setRegisterCount(toDayUserList.size());
        userExperienceAndUserStat.setAddTimeStr(addTimeStr);

        ///
        /// 累加数量
        Query query = new Query();
        query.with(new Sort(Sort.Direction.DESC, "addTime"));
        UserExperienceAndUserStat lastUserExperienceAndUserStat = getRoutingMongoOps().findOne(query, UserExperienceAndUserStat.class);
        if (lastUserExperienceAndUserStat != null) {
            userExperienceAndUserStat.setAppendTotalCount(lastUserExperienceAndUserStat.getAppendTotalCount() + userExperienceAndUserStat.getTotalCount());
            userExperienceAndUserStat.setAppendRegisterCount(lastUserExperienceAndUserStat.getAppendRegisterCount() + userExperienceAndUserStat.getRegisterCount());
            userExperienceAndUserStat.setAppendExperienceCount(lastUserExperienceAndUserStat.getAppendExperienceCount() + userExperienceAndUserStat.getExperienceCount());
        }
        else {
            userExperienceAndUserStat.setAppendTotalCount(Long.valueOf(userExperienceAndUserStat.getTotalCount()));
            userExperienceAndUserStat.setAppendRegisterCount(Long.valueOf(userExperienceAndUserStat.getRegisterCount()));
            userExperienceAndUserStat.setAppendExperienceCount(Long.valueOf(userExperienceAndUserStat.getExperienceCount()));
        }
        userExperienceAndUserStat.setAddTime(endTime);
        insertToMongo(userExperienceAndUserStat);
    }
}
