package com.business.core.utils;

import com.business.core.entity.task.TaskType;
import com.business.core.entity.user.UserRunStatistics;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by zhangtao on 2016/11/29.
 */
public class RunLevelUtil {

    /**
     * 获取基础跑步等级
     *
     * @param distance
     * @return
     */
    public static String getRunLevel(Long distance, Integer tenMileAbility, Integer haftMarathonAbility) {
        String result = "";
        if (distance == 0L) {
            result = TaskType.RUN_LEVEL_1.toString();
        } else if (0 < distance && distance < 10000) {
            result = TaskType.RUN_LEVEL_2.toString();
        } else if (10000 <= distance && distance < 20000) {
            result = TaskType.RUN_LEVEL_3.toString();
        } else if (20000 <= distance && distance < 30000) {
            result = TaskType.RUN_LEVEL_4.toString();
        } else if (30000 <= distance && distance < 50000) {
            result = TaskType.RUN_LEVEL_5.toString();
        } else if (50000 <= distance && distance < 70000) {
            result = TaskType.RUN_LEVEL_6.toString();
        } else if (70000 <= distance && distance < 100000) {
            result = TaskType.RUN_LEVEL_7.toString();
        } else if (100000 <= distance && distance < 200000) {
            result = TaskType.RUN_LEVEL_8.toString();
        } else if (200000 <= distance && distance < 300000) {
            result = TaskType.RUN_LEVEL_9.toString();
        } else if (300000 <= distance) {
            result = TaskType.RUN_LEVEL_10.toString();
        }

        if( 500000 <= distance && tenMileAbility >= UserRunStatistics.TEN_MILE_ABILITY_LEVEL_ONE) {
            result = TaskType.RUN_LEVEL_11.toString();
        }

        if( 1000000 <= distance && tenMileAbility >= UserRunStatistics.TEN_MILE_ABILITY_LEVEL_TWO) {
            result = TaskType.RUN_LEVEL_12.toString();
        }

        if( 1500000 <= distance && tenMileAbility >= UserRunStatistics.TEN_MILE_ABILITY_LEVEL_THREE) {
            result = TaskType.RUN_LEVEL_13.toString();
        }

        if( 2000000 <= distance && haftMarathonAbility >= UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_ONE) {
            result = TaskType.RUN_LEVEL_14.toString();
        }

        if( 3000000 <= distance && haftMarathonAbility >= UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_TWO) {
            result = TaskType.RUN_LEVEL_15.toString();
        }

        if( 5000000 <= distance && haftMarathonAbility >= UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_THREE) {
            result = TaskType.RUN_LEVEL_16.toString();
        }

        return result;
    }

    public static Integer getTenMileAbility(Long distance, Long time) {
        Double speed = ((double) distance) / (time / 1000);
        Double one = 10000D / (60 * 45);
        Double two = 10000D / (60 * 40);
        Double three = 10000D / (60 * 35);
        if(speed >= three) {
            return UserRunStatistics.TEN_MILE_ABILITY_LEVEL_THREE;
        } else if(speed >= two) {
            return UserRunStatistics.TEN_MILE_ABILITY_LEVEL_TWO;
        } else if(speed >= one) {
            return UserRunStatistics.TEN_MILE_ABILITY_LEVEL_ONE;
        } else {
            return UserRunStatistics.TEN_MILE_ABILITY_LEVEL_DEFAULT;
        }
    }

    public static Integer getHalfMarathonAbility(Long distance, Long time) {
        Double speed =  ((double) distance) / (time / 1000);
        Double one = 21500D / (60 * 100);
        Double two = 21500D / (60 * 95);
        Double three = 21500D / (60 * 90);
        if(speed >= three) {
            return UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_THREE;
        } else if(speed >= two) {
            return UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_TWO;
        } else if(speed >= one) {
            return UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_ONE;
        } else {
            return UserRunStatistics.HALF_MARATHON_ABILITY_LEVEL_DEFAULT;
        }
    }


    public static void main(String[] args) {
//        getTenMileAbility(10000L, 60 * 44L);
        System.out.println(getLevel(TaskType.RUN_LEVEL_12.toString()));
    }

    /**
     * 获取等级数字
     * @param runLevel
     * @return
     */
    public static Integer getLevel(String runLevel) {
        String reg = "[^0-9]";
        Pattern p = Pattern.compile(reg);
        Matcher m = p.matcher(runLevel);
        return Integer.parseInt(m.replaceAll("").trim());

    }
}
