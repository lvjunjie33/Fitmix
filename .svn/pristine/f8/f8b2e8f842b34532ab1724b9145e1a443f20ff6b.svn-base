package com.business.app.wd;

import com.business.core.client.FileCenterClient;
import com.business.core.entity.user.User;
import com.business.core.entity.wd.Speedway;
import com.business.core.entity.wd.SpeedwayHeedUser;
import com.business.core.entity.wd.SpeedwayRun;
import com.business.core.entity.wd.SpeedwayRunStat;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by edward on 2016/7/20.
 */
@Service
public class SpeedwayService {

    @Autowired
    private SpeedwayDao speedwayDao;
    @Autowired
    private UserCoreDao userCoreDao;

    public void addSpeedWayRun(Integer uid, String city, String wayId, Long bpm, Long bpmMatch, Long distance, Long calorie,
                               Long step, Long runTime, Long beginTime, Long endTime) {

        //万德的运动数据
        Speedway speedway = speedwayDao.findSpeedwayByCityAndWayId(city, wayId);
        if (speedway == null) {
            return;
        }
        SpeedwayRun run = new SpeedwayRun();
        run.setUid(uid);
        run.setSpeedwayId(speedway.getId());
        run.setBpm(bpm);
        run.setBpmMatch(bpmMatch);
        run.setDistance(distance);
        run.setCalorie(calorie);
        run.setStep(step);
        run.setRunTime(runTime);
        run.setBeginTime(beginTime);
        run.setEndTime(endTime);
        run.setAddTime(System.currentTimeMillis());
        speedwayDao.saveSpeedwayRun(run);

        //用户关注的赛道，以及今日的排名信息
        SpeedwayHeedUser speedwayHeedUser = speedwayDao.findSpeedwayHeedUserByUidAndSpeedwayId(uid, speedway.getId());
        Integer currentDate = Integer.parseInt(DateUtil.format(new Date(), "yyyyMMdd"));
        if (speedwayHeedUser == null) { //首次初始化
            speedwayHeedUser = new SpeedwayHeedUser();
            speedwayHeedUser.setSpeedwayId(speedway.getId());
            speedwayHeedUser.setUid(uid);
            speedwayHeedUser.setCurrentDate(currentDate);
            speedwayHeedUser.setHeedTime(System.currentTimeMillis());
            speedwayHeedUser.setSumCalorie(calorie);
            speedwayHeedUser.setSumDistance(distance);
            speedwayHeedUser.setSumStep(step);
            speedwayDao.saveSpeedwayHeedUser(speedwayHeedUser);
        } else {
            Update update = Update.update("lastOperationTime", System.currentTimeMillis());
            if (currentDate.equals(speedwayHeedUser.getCurrentDate())) {//今天的累计
                update.set("sumStep", speedwayHeedUser.getSumStep() + step).set("sumCalorie", speedwayHeedUser.getSumCalorie() + calorie).set("sumDistance", speedwayHeedUser.getSumDistance() + distance);
            } else {//初始化昨天的记录，并记录今天的数据
                update.set("currentDate", currentDate).set("sumStep", step).set("sumCalorie", calorie).set("sumDistance", distance);
            }
            speedwayDao.updateSpeedwayHeedUser(speedwayHeedUser.getId(), update);
        }
    }

    /**
     * 查询 用户关注的赛道
     *
     * @param uid 用户编号
     */
    public List<SpeedwayHeedUser> speedwayHeedList(Integer uid) {
        List<SpeedwayHeedUser> speedwayHeedUsers = speedwayDao.findSpeedwayHeedUserByUid(uid);
        if (CollectionUtils.isEmpty(speedwayHeedUsers)) {
            return Collections.EMPTY_LIST;
        }
        List<Integer> speedwayIds = CollectionUtil.buildList(speedwayHeedUsers, Integer.class, "speedwayId");
        Map<Integer, Speedway> speedwayMaps = CollectionUtil.buildMap(speedwayDao.findSpeedwayByIds(speedwayIds), Integer.class, Speedway.class, "id");
        for (SpeedwayHeedUser speedwayHeedUser : speedwayHeedUsers) {
            speedwayHeedUser.setLink("speedway/rank.htm?uid=" + speedwayHeedUser.getUid() + "&speedwayId=" + speedwayHeedUser.getSpeedwayId());
            Speedway speedway = speedwayMaps.get(speedwayHeedUser.getSpeedwayId());
            speedway.setBackgroundImage(FileCenterClient.buildUrl(speedway.getBackgroundImage()));
            speedwayHeedUser.setSpeedway(speedway);
        }
        return speedwayHeedUsers;
    }

    /**
     * 获取某个赛道的排名
     *
     * @param speedwayId 赛道编号
     * @param uid 用户编号
     */
    public Object[] rank(Integer speedwayId, Integer uid) {
        Date today = new Date();
        Date yesterday = DateUtil.getDayBefore(today);

        List<SpeedwayRunStat> oldSpeedwayRunStats = speedwayDao.findSpeedwayRunStats(speedwayId, DateUtil.formatDate2Int(yesterday)); //历史前十名排名
        SpeedwayRunStat oldSpeedwayRunStat = speedwayDao.findSpeedwayRunStatsByUid(speedwayId, DateUtil.formatDate2Int(yesterday), uid); //自己的历史信息

        List<SpeedwayHeedUser> speedwayHeedUsers = speedwayDao.findSpeedwayHeedUserBySpeedwayId(speedwayId, DateUtil.formatDate2Int(today), "uid", "sumCalorie", "sumStep", "sumDistance");//该赛道今日所有记录
        List<SpeedwayHeedUser> topSpeedwayHeedUsers = new ArrayList<>();// 今日前十排名

        List<Integer> uids = CollectionUtil.buildList(oldSpeedwayRunStats, Integer.class, "uid");
        uids.add(uid);

        Map<String, Object> info = new HashMap<>();
        if (oldSpeedwayRunStat != null) {
            info.put("sumCalorie", oldSpeedwayRunStat.getSumDistance());
            info.put("sumStep", oldSpeedwayRunStat.getSumStep());
            info.put("sumDistance", oldSpeedwayRunStat.getSumDistance());
            info.put("sumSort", oldSpeedwayRunStat.getSort());
        } else {
            info.put("sumCalorie", 0L);
            info.put("sumStep", 0L);
            info.put("sumDistance", 0L);
            info.put("sumSort", -1L);
        }

        info.put("calorie", 0L);
        info.put("step", 0L);
        info.put("distance", 0L);
        info.put("sort", -1L);

        for (int i = 0; i < speedwayHeedUsers.size(); i++) {
            if (i < 10) {
                SpeedwayHeedUser speedwayHeedUser = speedwayHeedUsers.get(i);
                speedwayHeedUser.setSort(i + 1);
                topSpeedwayHeedUsers.add(speedwayHeedUser);
                uids.add(speedwayHeedUsers.get(i).getUid());
            }
            if (speedwayHeedUsers.get(i).getUid().equals(uid)) {
                SpeedwayHeedUser speedwayHeedUser = speedwayHeedUsers.get(i);
                info.put("calorie", speedwayHeedUser.getSumCalorie());
                info.put("step", speedwayHeedUser.getSumStep());
                info.put("distance", speedwayHeedUser.getSumDistance());
                info.put("sort", i + 1);
            }
        }

        Map<Integer, User> userMap = CollectionUtil.buildMap(userCoreDao.findUserById(uids, User.STATE_ACTIVATES, "name", "avatar"), Integer.class, User.class, "id");
        for (SpeedwayHeedUser speedwayHeedUser : topSpeedwayHeedUsers) {
            User user = userMap.get(speedwayHeedUser.getUid());
            user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            speedwayHeedUser.setUser(user);
        }
        for (SpeedwayRunStat speedwayRunStat : oldSpeedwayRunStats) {
            User user = userMap.get(speedwayRunStat.getUid());
            user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            speedwayRunStat.setUser(user);
        }
        User user = userMap.get(uid);
        info.put("avatar", FileCenterClient.buildUrl(user.getAvatar()));
        info.put("name", user.getName());
        info.put("id", user.getId());

        info.put("sumCalorie", (long)info.get("calorie") + (long)info.get("sumCalorie"));
        info.put("sumStep", (long)info.get("step") + (long)info.get("sumStep"));
        info.put("sumDistance", (long)info.get("distance") + (long)info.get("sumDistance"));

        return new Object[]{info, topSpeedwayHeedUsers, oldSpeedwayRunStats};
    }
}
