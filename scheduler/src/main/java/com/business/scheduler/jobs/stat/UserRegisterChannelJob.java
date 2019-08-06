package com.business.scheduler.jobs.stat;

import com.business.core.entity.stat.UserRegisterChannelStat;
import com.business.core.entity.stat.UserRegisterTypeStat;
import com.business.core.entity.user.User;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.scheduler.base.BaseJob;
import org.bson.types.ObjectId;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import java.util.*;

/**
 * Created by sin on 2015/8/24.
 */
@Service
public class UserRegisterChannelJob extends BaseJob {

    private Logger logger = LoggerFactory.getLogger(getClass());

    public static void main(String args[]) {
    }

    @Override
    public void execute () {
        Date date = DateUtil.addDate(Calendar.HOUR_OF_DAY, -1);
        Long beginTime = DateUtil.getHourBegin(date).getTime();
        Long endTime = DateUtil.getHourEnd(date).getTime();
        registerChannel(beginTime, endTime);
    }

    public void registerChannel(Long beginTime, Long endTime) {

        // 注册用户
        List<User> registerUserList;
        {
            Query query = Query.query(Criteria.where("addTime").gte(beginTime).lte(endTime));
            includeFields(query, "id", "name", "addTime", "channel", "registerChannel", "registerType");
            registerUserList = getRoutingMongoOps().find(query, User.class);
        }

        // 登陆用户 log
//        List<UserLoginLog> userLoginLogList = Collections.emptyList();
//        {
//            Query query = Query.query(Criteria.where("addTime").gte(beginTime).lte(endTime));
//            userLoginLogList = getRoutingMongoOps().find(query, UserLoginLog.class);
//        }
//
        Map<String, List<User>> userMultimap = CollectionUtil.buildMultimap(registerUserList, String.class, User.class, "registerChannel");
        userMultimap.remove(null);

        // 今日注册用户
        for (Map.Entry<String, List<User>> entry : userMultimap.entrySet()) {
            // 今日注册用户 类型
            List<UserRegisterTypeStat> userRegisterTypeStatList = new ArrayList<>();
            for (User user : entry.getValue()) {
                UserRegisterTypeStat userRegisterTypeStat = new UserRegisterTypeStat();
                userRegisterTypeStat.setUid(user.getId());
                userRegisterTypeStat.setChannel(user.getChannel());
                userRegisterTypeStat.setRegisterType(user.getRegisterType());
                userRegisterTypeStat.setAddTime(beginTime);
                insertToMongo(userRegisterTypeStat);
                userRegisterTypeStatList.add(userRegisterTypeStat);
            }

            // 注册用户渠道
            UserRegisterChannelStat userRegisterChannelStat = new UserRegisterChannelStat();
            userRegisterChannelStat.setLoginCount(entry.getValue().size());
            userRegisterChannelStat.setChannel(entry.getKey());
            userRegisterChannelStat.setHour(DateUtil.getField(DateUtil.parse(beginTime), Calendar.HOUR_OF_DAY));
            userRegisterChannelStat.setAddTime(beginTime);
            userRegisterChannelStat.setUserRegisterTypeStatId(CollectionUtil.buildList(userRegisterTypeStatList, ObjectId.class, "id"));
            insertToMongo(userRegisterChannelStat);
        }
    }
}
