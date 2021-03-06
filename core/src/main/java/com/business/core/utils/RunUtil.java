package com.business.core.utils;

import com.business.core.entity.SysParam;

/**
 * Created by edward on 2017/12/1.
 *
 * 运动相关的计算公式
 *
 * 步频：走路或者跑步时，每分钟脚步落地的次数，例如：在1分钟内，左右脚共踏出150步，那么你的步频就是150次/分钟，如果左右脚共踏出180步，那么步频就是180次/分钟。
 */
public class RunUtil {

    /**
     * 配速计算公式
     *
     * @param distance 里程(m 米)
     * @param time 时间 (ms 毫秒)
     */
    public static String pace(long distance, long time) {
        double dis = 1.0 * distance / 1000;
        time = time / 1000;
        int s = (int) (time / dis);
        int m = s / 60;
        s = s % 60;
        String ss = "00";
        String mm = "00";
        if (m > 0) {
            if (m < 10) {
                mm = "0" + m;
            } else {
                mm = m + "";
            }
        }
        if (s > 0) {
            if (s < 10) {
                ss = "0" + s;
            } else {
                ss = s + "";
            }
        }
        return mm + "′" + ss +"″";
    }

    /**
     * 获取整形配速
     *
     * @param pace 配速
     */
    public static Integer getPaceInt(String pace) {
        return Integer.parseInt(pace.replace("′","").replace("″", ""));
    }

    /**
     * 获取配速级别
     *
     * {@link com.business.core.entity.user.info.UserRunStat#level}
     */
    public static Integer getPaceLevel(String pace) {
        Integer paceInt = getPaceInt(pace);
        if (paceInt >= 1000) {
            return 1;
        }else if (paceInt < 1000 && paceInt >= 800) {
            return 2;
        }
        else if (paceInt < 800 && paceInt >= 630) {
            return 3;
        }
        else if (paceInt < 630 && paceInt >= 530) {
            return 4;
        }
        else if(paceInt>0 && paceInt<530){
            return 5;
        }
        else{
            return 1;
        }

    }

    public static void main(String[] args) {
//        Double di = 5D;
//        Long m = 70L;
//        System.out.println(m /di);

        Long d = 6160L;
        Long runTime = 21460000L;
        System.out.print(RunUtil.pace(d, runTime));
    }
}
