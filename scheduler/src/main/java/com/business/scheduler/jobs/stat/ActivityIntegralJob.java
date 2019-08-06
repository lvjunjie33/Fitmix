package com.business.scheduler.jobs.stat;

import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.BaseMongoDaoSupport;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.activity.ActivityCoreDao;
import com.business.core.sort.SortFactory;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by edward on 2016/5/27.
 *
 * 积分赛事 积分统计
 */
@Service
public class ActivityIntegralJob extends BaseMongoDaoSupport {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Scheduled(cron = "0 0/2 * * * ?")
    public void execute() {DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);

        //停用
        if (true) {
            return;
        }

        ActivityCoreDao activityCoreDao = BeanManager.getBean(ActivityCoreDao.class);

        Date yesterday = DateUtil.getDayBegin(DateUtil.getDayBefore(new Date()));

        //正在举办的积分赛事列表
        List<Activity> activityList = activityCoreDao.findStartActivity(yesterday.getTime(),
                Activity.RELEASE_STATUS_RELEASE, Activity.STATUS_NORMAL, Activity.TYPE_INTEGRAL, "themeName", "type", "cityTarget");
        if (CollectionUtils.isEmpty(activityList)) {
            return;
        }

        List<Integer> types = new ArrayList<>();
        types.add(UserRun.TYPE_UNKNOWN);
        types.add(UserRun.TYPE_RUN);

        for (Activity activity : activityList) {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {

            }
            List<ActivityIntegralStat> stats = new ArrayList<>();

            //报名的用户列表
            List<ActivitySignUp> activitySignUps = activityCoreDao.findActivitySignUpByActivityId(activity.getId());
            //没有报名则不统计
            if (CollectionUtils.isEmpty(activitySignUps)) {
                continue;
            }

            for (ActivitySignUp activitySignUp : activitySignUps) {
                ActivityIntegralStat stat = iniActivityIntegralStat(activitySignUp, yesterday);
                stats.add(stat);
                if (activitySignUp.getUserId() == null) {
                    stats.remove(stat);
                    continue;
                }
                //查询用户信息
                User user = defaultDao.findById(User.class, activitySignUp.getUserId(), "taoBaoIp.city_id", "taoBaoIp.country_id", "taoBaoIp.region_id");
                if (user == null) {
                    stats.remove(stat);
                    continue;
                }
                totalStat(yesterday, types, stat, defaultDao);
                congestStat(activity, activitySignUp, stat, user, defaultDao);
                if (stat.getSumIntegral() == 0 && !check(activity, activitySignUp, user)) {
                    stats.remove(stat);
                }
            }
            Collections.sort(stats, SortFactory.ACTIVITY_INTEGRAL_STAT_SORT);

            for (int i = 0; i < stats.size(); i++) {
                stats.get(i).setSort(i+1);
                defaultDao.save(stats.get(i));
            }
        }
    }

    private void congestStat(Activity activity, ActivitySignUp activitySignUp, ActivityIntegralStat stat, User user, DefaultDao defaultDao) {
        Query query = Query.query(Criteria.where("activityId").is(activitySignUp.getActivityId()).and("uid").is(activitySignUp.getUserId()));
        query.with(new Sort(Sort.Direction.DESC, "statDate"));
        ActivityIntegralStat activityIntegralStat = BeanManager.getBean(DefaultDao.class).findOne(query, ActivityIntegralStat.class);
        boolean isOk = check(activity, activitySignUp, user);

        if ( isOk && activityIntegralStat != null) {//计算累计数据
            stat.setSumDistance(activityIntegralStat.getSumDistance() + stat.getSumDistance());
            stat.setSumStep(activityIntegralStat.getSumStep() + stat.getSumStep());
            stat.setSumIntegral(activityIntegralStat.getSumIntegral() + stat.getSumIntegral());
        } else if (activityIntegralStat != null) {
            stat.setSumDistance(activityIntegralStat.getSumDistance());
            stat.setSumStep(activityIntegralStat.getSumStep());
            stat.setSumIntegral(activityIntegralStat.getSumIntegral());
        }

        //前十名 额外赠送1积分
        Query query1 = Query.query(Criteria.where("activityId").is(activity.getId()).and("sort").lte(10)
                .and("statDate").is(stat.getStatDate() - 1).and("uid").is(activitySignUp.getUserId()));
        ActivityIntegralStat oldStat = defaultDao.findOne(query1, ActivityIntegralStat.class);
        if (oldStat != null) {
            if (isOk) {
                stat.setSumIntegral(stat.getIntegral() + oldStat.getSumIntegral() + 1);
                stat.setIntegral(stat.getIntegral() + 1);
            } else {//如果在排行榜里面，但是当日不再指定积分赛事区域，只增加奖励积分
                stat.setSumIntegral(oldStat.getSumIntegral() + 1);
                stat.setIntegral(1L);
            }

        }
    }

    /**
     * 合计昨日 个人积分
     */
    private void totalStat(Date yesterday, List<Integer> types, ActivityIntegralStat stat, DefaultDao defaultDao) {
        Long begin = DateUtil.getDayBegin(yesterday).getTime();
        Long end = DateUtil.getDayEnd(yesterday).getTime();
        Query query = Query.query(Criteria.where("startTime").gte(begin).lte(end).and("type").in(types).and("uid").is(stat.getUid()));
        //当日该的用户运动记录
        List<UserRun> userRuns = defaultDao.query(query, UserRun.class, "step", "distance", "uid", "startLng", "startLat", "model", "runTime");

        if (!CollectionUtils.isEmpty(userRuns)) {

            DecimalFormat decimalFormat = new DecimalFormat("#");
            for (UserRun run : userRuns) {
                if (run.getRunTime() == null || run.getDistance() == null) {
                    continue;
                }
                Double speed = (run.getRunTime() / 1000 / 60) / (1.0 * run.getDistance() / 1000);
                ///平均配速必须大于3分钟
                ///app点击开始运动进行室外运动
                if (UserRun.MODAL_TYPE_INDOOR.equals(run.getModel()) && speed > 3) {

                    stat.setStep(stat.getStep() + run.getStep());
                    Long setIntegral = Long.parseLong(decimalFormat.format(stat.getStep() * 1.0 / 5000));

                    stat.setDistance(stat.getDistance() + run.getDistance());
                    Long distanceIntegral = Long.parseLong(decimalFormat.format(stat.getDistance()* 1.0 / 3000));

                    if (setIntegral > distanceIntegral) {
                        stat.setIntegral(setIntegral);
                    } else {
                        stat.setIntegral(distanceIntegral);
                    }
                }
            }
            //计算总和
            stat.setSumDistance(stat.getDistance());
            stat.setSumStep(stat.getStep());
            stat.setSumIntegral(stat.getIntegral());
        }
    }

    /**
     * 初始化个人统计对象
     */
    private ActivityIntegralStat iniActivityIntegralStat(ActivitySignUp activitySignUp, Date yesterday) {
        ActivityIntegralStat stat = new ActivityIntegralStat();
        stat.setSumDistance(0L);
        stat.setSumIntegral(0L);
        stat.setSumStep(0L);

        stat.setDistance(0L);
        stat.setStep(0L);
        stat.setIntegral(0L);

        stat.setAddTime(System.currentTimeMillis());
        stat.setStatDate(DateUtil.formatDate2Int(yesterday));

        Integer uid = activitySignUp.getUserId();
        stat.setUid(uid);
        stat.setActivityId(activitySignUp.getActivityId());
        return stat;
    }

    /**
     * 检查该用户是否处于积分赛事指定区域,不在则不累加当前积分
     */
    private boolean check(Activity activity, ActivitySignUp activitySignUp, User user) {
        //用户ip地址为空
        if (user == null || user.getTaoBaoIp() == null) {
            return false;
        }

        //地域标签
        Integer cityTarget = activity.getCityTarget();

        if (cityTarget == null) {
            return false;
        }

        TaoBaoIp taoBaoIp = user.getTaoBaoIp();
        String userCityCode = taoBaoIp.getCity_id();
        String userRegionId = taoBaoIp.getRegion_id();

        //支持的城市 和省份
        switch (cityTarget) {
            case 201:
                if (!TaoBaoIp.REGION_ID_310000.equals(userRegionId)) {
                    return false;
                }
                break;
            case 202:
                if (!TaoBaoIp.REGION_ID_410000.equals(userRegionId)) {
                    return false;
                }
                break;
            case 1:
                if (!TaoBaoIp.CITY_CODE_440300.equals(userCityCode)) {
                    return false;
                }
                break;
        }
        return true;
    }
}
