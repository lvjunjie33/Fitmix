package com.business.work.userRun;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.userRun.UserRunCoreDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.work.user.UserDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by zhangtao on 2016/4/20.
 */
@Service
public class UserRunService {

    @Autowired
    private UserRunDao userRunDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserRunCoreDao userRunCoreDao;
    @Autowired
    private UserDao userDao;

    public void page(Page<UserRun> page) {
        Map<String, Object> filter = page.getFilter();
        if (filter.containsKey("type")) {
            if (filter.get("type").equals(UserRun.TYPE_RUN)) {
                filter.put("types", Arrays.asList(UserRun.TYPE_RUN, UserRun.TYPE_UNKNOWN));
            } else {
                filter.put("types", Arrays.asList(filter.get("type")));
            }
        } else {
            filter.put("types", Arrays.asList(UserRun.TYPE_RUN, UserRun.TYPE_UNKNOWN));
        }
        userRunDao.page(page);
    }

    public UserRun allUserRuns(Integer uid) {
        List<UserRun> userRuns = userRunDao.allUserRunsByUid(uid, Arrays.asList(UserRun.TYPE_RUN, UserRun.TYPE_UNKNOWN),
                "calorie", "distance", "step", "runTime");
        UserRun sumUserRun = new UserRun();
        Long distance = 0L;
        Long step = 0L;
        Long calorie = 0L;
        Long runTime = 0L;

        for (UserRun userRun : userRuns) {
            if (userRun.getDistance() != null) {
                distance += userRun.getDistance();
            }
            if (userRun.getStep() != null) {
                step += userRun.getStep();
            }
            if (userRun.getRunTime() != null) {
                runTime += userRun.getRunTime();
            }
            if (userRun.getCalorie() != null) {
                calorie += userRun.getCalorie();
            }
        }
        sumUserRun.setDistance(distance);
        sumUserRun.setCalorie(calorie);
        sumUserRun.setStep(step);
        sumUserRun.setRunTime(runTime / 1000 / 60);
        return sumUserRun;
    }

    public void restoreTotal(Integer uid) {
        List<UserRun> userRuns = userRunDao.allUserRunsByUid(uid, Arrays.asList(UserRun.TYPE_RUN, UserRun.TYPE_UNKNOWN));

        UserRun lastRun = new UserRun();

        UserRun sumUserRun = new UserRun();
        Long distance = 0L;
        Long step = 0L;
        Long calorie = 0L;
        Long runTime = 0L;

        for (UserRun userRun : userRuns) {
            lastRun = userRun;
            if (userRun.getDistance() != null && userRun.getDistance() < 3000000) {
                distance += userRun.getDistance();
            }
            if (userRun.getStep() != null && userRun.getStep() < 1000000) {
                step += userRun.getStep();
            }
            if (userRun.getRunTime() != null && userRun.getRunTime() < 1000 * 60 * 60 * 24) {
                runTime += userRun.getRunTime();
            }
            if (userRun.getCalorie() != null && userRun.getCalorie() < 15000) {
                calorie += userRun.getCalorie();
            }
        }
        sumUserRun.setDistance(distance);
        sumUserRun.setCalorie(calorie);
        sumUserRun.setStep(step);
        sumUserRun.setRunTime(runTime / 1000 / 60);

        userCoreDao.updateUserById(uid,
                Update.update("lastRun", lastRun).set("distance", sumUserRun.getDistance()).set("step",sumUserRun.getStep())
                        .set("calorie", sumUserRun.getCalorie()).set("runTime", sumUserRun.getRunTime()));
    }

    /**
     * 用户运动时间统计
     *
     * @param page 分页对象
     */
    public void userRunTimeStat(Page<UserRunStat> page) {
        userRunDao.userRunStatPage(page);
    }

    /**
     * 用户运动统计
     *
     * @param page 分页对象
     */
    public void runMonthRank(Page<UserRunStat> page) {
        userRunCoreDao.runMonthRank(page);
        List<UserRunStat> stats = page.getResult();
        if (!CollectionUtils.isEmpty(stats)) {
            Set<Integer> uids = new HashSet<>();
            for (UserRunStat s : stats) {
                uids.add(s.getUid());
            }
            List<User> users = userDao.findUserByUid(uids, "gender", "avatar", "name", "mobile", "height", "weight");
            Map<Integer, User> userMap = new HashMap<>();
            for (User u : users) {
                u.setAvatar(FileCenterClient.buildUrl(u.getAvatar()));
                userMap.put(u.getId(), u);
            }
            for (UserRunStat s : stats) {
                s.setUser(userMap.get(s.getUid()));
            }
        }
    }

    public void remove() {
        userRunDao.remove();
    }
}
