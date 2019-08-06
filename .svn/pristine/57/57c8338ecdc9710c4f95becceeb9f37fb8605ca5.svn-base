package com.business.core.sort;

import com.alibaba.fastjson.JSONObject;
import com.business.core.entity.Dictionary;
import com.business.core.entity.IncIdEntity;
import com.business.core.entity.channel.ChannelUserStat;
import com.business.core.entity.runPlan.Stages;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.wd.SpeedwayRunStat;

import java.util.Comparator;
import java.util.Map;

/**
 *
 * Created by edward on 2017/8/29.
 */
public class SortFactory {

    public static final Comparator<Dictionary> DICTIONARY_SORT = new Comparator<Dictionary>() {
        @Override
        public int compare(Dictionary o1, Dictionary o2) {
            if (o1.getType() > o2.getType()) {
                return -1;
            } else if(o1.getType() == o2.getType()) {
                return 0;
            }
            return 1;
        }
    };

    public static final Comparator<JSONObject> WX_RUN_SORT = new Comparator<JSONObject>() {
        @Override
        public int compare(JSONObject o1, JSONObject o2) {
            Object o = o1.get("totalDistance");
            if (((double)o1.get("totalDistance")) >  ((double)o2.get("totalDistance"))) {
                return -1;
            } else  if (((double)o1.get("totalDistance")) == ((double)o2.get("totalDistance"))) {
                return 0;
            }
            return 1;
        }
    };

    public static final Comparator<String> STR_SORT = new Comparator<String>() {
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    };

    public static final Comparator<Map.Entry<String, ?>> MAP_ENTRY_SORT = new Comparator<Map.Entry<String, ?>>() {
        @Override
        public int compare(Map.Entry<String, ?> o1, Map.Entry<String, ?> o2) {
            return (o1.getKey()).toString().compareTo(o2.getKey());
        }
    };

    public static final Comparator<ActivityIntegralStat> ACTIVITY_INTEGRAL_STAT_SORT = new Comparator<ActivityIntegralStat>() {
        @Override
        public int compare(ActivityIntegralStat o1, ActivityIntegralStat o2) {
            if(o1.getSumIntegral() < o2.getSumIntegral()){
                return 1;
            } else if(o1.getSumIntegral() == o2.getSumIntegral()) {
                if (o1.getSumDistance() < o2.getSumDistance()) {
                    return 1;
                } else {
                    return -1;
                }
            } else {
                return -1;
            }
        }
    };

    public static final Comparator<SpeedwayRunStat> SPEEDWAY_RUN_STAT_SORT = new Comparator<SpeedwayRunStat>() {
        @Override
        public int compare(SpeedwayRunStat o1, SpeedwayRunStat o2) {
            if(o1.getSumCalorie() < o2.getSumCalorie()){
                return 1;
            } else if(o1.getSumCalorie() == o2.getSumCalorie()) {
                return 0;
            } else {
                return -1;
            }
        }
    };

    public static final Comparator<UserRun> USER_RUN_SORT = new Comparator<UserRun>() {
        @Override
        public int compare(UserRun o1, UserRun o2) {
            return o1.getDistance() < o2.getDistance() ? -1 : 1;
        }
    };

    public static final Comparator<ChannelUserStat> CHANNEL_USER_STAT_SORT = new Comparator<ChannelUserStat>() {
        @Override
        public int compare(ChannelUserStat o1, ChannelUserStat o2) {
            return o1.getStatDay() - o2.getStatDay();
        }
    };

    public static final Comparator<Stages> STAGES_SORT = new Comparator<Stages>() {
        @Override
        public int compare(Stages o1, Stages o2) {
            return o1.getDate() - o2.getDate();
        }
    };

    public static final Comparator<User> USER_SORT = new Comparator<User>() {
        @Override
        public int compare(User o1, User o2) {
            if (o1.getId() > o2.getId()) {
                return 1;
            } else if (o1.getId() == o2.getId()) {
                return 0;
            } else {
                return -1;
            }
        }
    };
}
