package com.business.msg.server.task;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.wd.Speedway;
import com.business.core.entity.wd.SpeedwayRun;
import com.business.core.entity.wd.SpeedwayRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.sort.SortFactory;
import com.business.core.utils.BeanManager;
import com.business.core.utils.DateUtil;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.*;

/**
 * Created by edward on 2017/9/19.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_SPEEDWAY_RUN_TASK)
public class SpeedwayRunTask implements RedisConcurrentlyCommand {

    @Override
    public void execute(String msg) {

    }

    protected void handle(Date currentDate) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Date yesterday = DateUtil.getDayBegin(DateUtil.getDayBefore(currentDate));
        //获取所有赛道信息
        List<Speedway> speedways = defaultDao.findAll(Speedway.class);


        Long begin = DateUtil.getDayBegin(yesterday).getTime();
        Long end = DateUtil.getDayEnd(yesterday).getTime();

        //当日所有的用户运动记录
        List<SpeedwayRun> speedwayRuns = defaultDao.query(Query.query(Criteria.where("beginTime").gte(begin).lte(end)), SpeedwayRun.class);

        Map<Integer, Map<Integer, SpeedwayRunStat>> stats = new HashMap<>();

        for (Speedway speedway : speedways) {
            Map<Integer, SpeedwayRunStat> stat = new HashMap<Integer, SpeedwayRunStat>();
            stats.put(speedway.getId(), stat);

            for (SpeedwayRun speedwayRun : speedwayRuns) {
                if (!speedway.getId().equals(speedwayRun.getSpeedwayId()) || speedwayRun.getUid() == null || speedwayRun.getUid() <= 0) {//非一个赛道数据
                    continue;
                }

                SpeedwayRunStat speedwayRunStat = stat.get(speedwayRun.getUid());
                if (speedwayRunStat == null) {
                    speedwayRunStat = new SpeedwayRunStat();
                    speedwayRunStat.setSumStep(0L);
                    speedwayRunStat.setSumDistance(0L);
                    speedwayRunStat.setSumCalorie(0L);

                    speedwayRunStat.setStep(0L);
                    speedwayRunStat.setDistance(0L);
                    speedwayRunStat.setCalorie(0L);

                    speedwayRunStat.setSpeedwayId(speedway.getId());
                    speedwayRunStat.setUid(speedwayRun.getUid());

                    speedwayRunStat.setAddTime(yesterday.getTime());
                    speedwayRunStat.setStatDate(DateUtil.formatDate2Int(yesterday));

                    stat.put(speedwayRunStat.getUid(), speedwayRunStat);
                }

                speedwayRunStat.setStep(speedwayRun.getStep() + speedwayRunStat.getStep());
                speedwayRunStat.setDistance(speedwayRun.getDistance() + speedwayRunStat.getDistance());
                speedwayRunStat.setCalorie(speedwayRun.getCalorie() + speedwayRunStat.getCalorie());

                speedwayRunStat.setSumStep(speedwayRunStat.getStep());
                speedwayRunStat.setSumDistance(speedwayRunStat.getDistance());
                speedwayRunStat.setSumCalorie(speedwayRunStat.getCalorie());
            }

            for (Integer uid : stat.keySet()) {//合计用户的赛道运动数据
                Query query = Query.query(Criteria.where("uid").is(uid).and("speedwayId").is(speedway.getId()));
                query.with(new Sort(Sort.Direction.DESC, "statDate"));
                SpeedwayRunStat oldSpeedwayRunStat = defaultDao.findOne(query, SpeedwayRunStat.class, "sumStep", "sumDistance", "sumCalorie");

                SpeedwayRunStat newSpeedwayRunStat = stat.get(uid);
                if (oldSpeedwayRunStat != null) {
                    newSpeedwayRunStat.setSumDistance(oldSpeedwayRunStat.getSumDistance() + newSpeedwayRunStat.getSumDistance());
                    newSpeedwayRunStat.setSumCalorie(oldSpeedwayRunStat.getSumCalorie() + newSpeedwayRunStat.getSumCalorie());
                    newSpeedwayRunStat.setSumStep(oldSpeedwayRunStat.getSumStep() + newSpeedwayRunStat.getSumStep());
                }
            }

            List<SpeedwayRunStat> speedwayRunStats = new ArrayList<>(stat.values());
            Collections.sort(speedwayRunStats, SortFactory.SPEEDWAY_RUN_STAT_SORT );
            for (int i = 0; i < speedwayRunStats.size(); i++) {
                speedwayRunStats.get(i).setSort(i+1);
            }

            for (SpeedwayRunStat speedwayRunStat : speedwayRunStats) {
                BeanManager.getBean(DefaultDao.class).save(speedwayRunStat);
            }
        }
    }


}
