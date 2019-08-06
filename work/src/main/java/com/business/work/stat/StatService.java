package com.business.work.stat;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.DicConstants;
import com.business.core.entity.*;
import com.business.core.entity.Dictionary;
import com.business.core.entity.logs.SnapTable;
import com.business.core.entity.logs.UserBehaviorLog;
import com.business.core.entity.stat.*;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.logs.UserBehaviorLogCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.DicUtil;
import com.business.work.user.UserDao;
import com.business.work.userRun.UserRunDao;
import org.apache.commons.lang3.math.NumberUtils;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.*;

/**
 * Created by Administrator on 2015/6/9 0009.
 */
@Service
public class StatService {

    @Autowired
    private StatDao statDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserBehaviorLogCoreDao userBehaviorLogCoreDao;
    @Autowired
    private UserDao userDao;
    @Autowired
    private UserRunDao userRunDao;

    public Object[] userStat (Long beginTime, Long endTime) {

        List<UserGrowthLossStat> userGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(beginTime, endTime);
        List<UserActiveRetainedStat> userActiveRetainedStatList = statDao.findUserActiveRetainedStatByGteAddTimeAndLte(beginTime, endTime);

        /// 处理 UserGrowthLossStat 数据
//        Map<String, List<UserGrowthLossStat>> userGrowthLossStatMultimap = CollectionUtil.buildMultimap(userGrowthLossStatList, String.class, UserGrowthLossStat.class, "strAddTime");
        String toDay[] = new String[]{"今天", "昨天", "前天"};
        int toDayBool = 0;
        List<UserGrowthLossStat> toDayUserGrowthLossStat = new ArrayList<>(toDay.length);

        String strAddTime = "";
        for (UserGrowthLossStat userGrowthLossStat :userGrowthLossStatList) {
            if (strAddTime.equals(userGrowthLossStat.getStrAddTime())) {
                continue;
            }
            else {
                strAddTime = userGrowthLossStat.getStrAddTime();
                userGrowthLossStat.setToDay(toDay[toDayBool]);
                toDayUserGrowthLossStat.add(userGrowthLossStat);
                toDayBool++;
            }
        }
//        for (Map.Entry<String, List<UserGrowthLossStat>> entry : userGrowthLossStatMultimap.entrySet()) {
//            UserGrowthLossStat userGrowthLossStat = entry.getValue().get(0);
//            userGrowthLossStat.setToDay(toDay[toDayBool]);
//            toDayUserGrowthLossStat.add(userGrowthLossStat);
//            toDayBool++;
//        }

        toDay = new String[]{"前一天", "前二天", "前三天"};
        toDayBool = 0;

        /// 处理 userActiveRetainedStatList 数据
        List<UserActiveRetainedStat> toDayUserActiveRetainedStat = new ArrayList<>(toDay.length);
        for (UserActiveRetainedStat userActiveRetainedStat : userActiveRetainedStatList) {
            userActiveRetainedStat.setToDay(toDay[toDayBool]);
            toDayUserActiveRetainedStat.add(userActiveRetainedStat);
            toDayBool++;
        }
        return new Object[]{toDayUserGrowthLossStat, toDayUserActiveRetainedStat};
    }

    public Echarts userGrowthLossStat (Integer today, Integer data) {

        /// today : 1 今天 2 一周 3 一月
        List<UserGrowthLossStat> firstWeekUserGrowthLossStatList = null, lastWeekUserGrowthLossStatList = null;
        Echarts echarts = new Echarts();
        switch (today) {
            case 1: // 今天 和 明天对比
                echarts.setLegendData(Arrays.asList("今天", "昨天"));
                lastWeekUserGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(DateUtil.getDayBegin(new Date()).getTime(), DateUtil.getDayEnd(new Date()).getTime());
                firstWeekUserGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, -1)).getTime(), DateUtil.getDayEnd(DateUtil.addDate(Calendar.DAY_OF_YEAR, -1)).getTime());
                break;
            case 2: // 本周 和 上周对比
                echarts.setLegendData(Arrays.asList("本周", "上周"));
                Integer week = DateUtil.getField(new Date(), Calendar.DAY_OF_WEEK) - 1; // 星期几
                lastWeekUserGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, -week + 1)).getTime(), DateUtil.getDayEnd(new Date()).getTime());
                firstWeekUserGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, -7 - week + 1)).getTime(), DateUtil.getDayEnd(DateUtil.addDate(Calendar.DAY_OF_YEAR, -week)).getTime());
//                System.out.println(DateUtil.format(DateUtil.addDate(Calendar.DAY_OF_YEAR, -7 - week + 1), "yyyy-MM-dd HH:mm:ss:ms"));
//                System.out.println(DateUtil.format(DateUtil.addDate(Calendar.DAY_OF_YEAR, -week), "yyyy-MM-dd HH:mm:ss:ms"));
                break;
            case 3: // 本月 与 上月对比
                echarts.setLegendData(Arrays.asList("本月", "上月"));
                lastWeekUserGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.getMonthBegin(new Date())).getTime(), DateUtil.getDayEnd(DateUtil.getMonthEnd(new Date())).getTime());
                firstWeekUserGrowthLossStatList = statDao.findUserGrowthLossStatByGteAddTimeAndLte(DateUtil.getMonthBegin(DateUtil.addDate(DateUtil.getMonthBegin(new Date()), Calendar.DAY_OF_YEAR, -1)).getTime(), DateUtil.addDate(DateUtil.getMonthBegin(new Date()), Calendar.DAY_OF_YEAR, -1).getTime());
                break;
        }

        /// data : 1 新用户 2 活跃用户 3 启动次数
        List<Object> objects = new ArrayList<>(2);
        switch (data) {
            case 1: // 新用户  （此处会处理，值的顺序）
                List<Integer> lastWeekValue = CollectionUtil.buildList(lastWeekUserGrowthLossStatList, Integer.class, "newAddUserCount");
                List<Integer> newLastWeekValue = new ArrayList<>(lastWeekValue.size());
                for (int i = lastWeekValue.size(); i > 0; i--) {
                    newLastWeekValue.add(lastWeekValue.get(i - 1));
                }

                List<Integer> firstWeekValue = CollectionUtil.buildList(firstWeekUserGrowthLossStatList, Integer.class, "newAddUserCount");
                List<Integer> newFirstWeekValue = new ArrayList<>(lastWeekValue.size());
                for (int i = firstWeekValue.size(); i > 0; i--) {
                    newFirstWeekValue.add(firstWeekValue.get(i - 1));
                }
                objects.add(newLastWeekValue);
                objects.add(newFirstWeekValue);
                break;
            case 2: // 活跃用户

                Map<Integer, List<UserGrowthLossStat>> lastUserGrowthLossStatMultimap = CollectionUtil.buildMultimap(lastWeekUserGrowthLossStatList, Integer.class, UserGrowthLossStat.class, "hour");
                List<Integer> lastCountUserGrowthLossStatList = new ArrayList<>();

                for (int i = 0; i < 24; i++) {
                    Integer count = 0;
                    if (!CollectionUtils.isEmpty(lastUserGrowthLossStatMultimap.get(i))) {
                        for (UserGrowthLossStat userGrowthLossStat :  lastUserGrowthLossStatMultimap.get(i)) {
                            count += userGrowthLossStat.getUserActiveCount();
                        }
                    }
                    lastCountUserGrowthLossStatList.add(count);
                }
                objects.add(lastCountUserGrowthLossStatList);

                Map<Integer, List<UserGrowthLossStat>> firstUserGrowthLossStatMultimap = CollectionUtil.buildMultimap(firstWeekUserGrowthLossStatList, Integer.class, UserGrowthLossStat.class, "hour");
                List<Integer> firstCountUserGrowthLossStatList = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    Integer count = 0;
                    if (!CollectionUtils.isEmpty(firstUserGrowthLossStatMultimap.get(i))) {
                        for (UserGrowthLossStat userGrowthLossStat :  firstUserGrowthLossStatMultimap.get(i)) {
                            count += userGrowthLossStat.getUserActiveCount();
                        }
                    }
                    firstCountUserGrowthLossStatList.add(count);
                }
                objects.add(firstCountUserGrowthLossStatList);
                break;
            case 3: // 启动次数 - 用户登录次数
                Map<Integer, List<UserGrowthLossStat>> lastLoginTimesWeekUserGrowthLossStatList = CollectionUtil.buildMultimap(lastWeekUserGrowthLossStatList, Integer.class, UserGrowthLossStat.class, "hour");
                List<Integer> lastLoginTimesUserGrowthLossStatList = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    Integer count = 0;
                    if (!CollectionUtils.isEmpty(lastLoginTimesWeekUserGrowthLossStatList.get(i))) {
                        for (UserGrowthLossStat userGrowthLossStat :  lastLoginTimesWeekUserGrowthLossStatList.get(i)) {
                            count += userGrowthLossStat.getLoginTimes();
                        }
                    }
                    lastLoginTimesUserGrowthLossStatList.add(count);
                }
                objects.add(lastLoginTimesUserGrowthLossStatList);

                Map<Integer, List<UserGrowthLossStat>> firstLoginTimesWeekUserGrowthLossStatList = CollectionUtil.buildMultimap(firstWeekUserGrowthLossStatList, Integer.class, UserGrowthLossStat.class, "hour");
                List<Integer> firstLoginTimesUserGrowthLossStatList = new ArrayList<>();
                for (int i = 0; i < 24; i++) {
                    Integer count = 0;
                    if (!CollectionUtils.isEmpty(firstLoginTimesWeekUserGrowthLossStatList.get(i))) {
                        for (UserGrowthLossStat userGrowthLossStat :  firstLoginTimesWeekUserGrowthLossStatList.get(i)) {
                            count += userGrowthLossStat.getLoginTimes();
                        }
                    }
                    firstLoginTimesUserGrowthLossStatList.add(count);
                }
                objects.add(firstLoginTimesUserGrowthLossStatList);
                break;
        }
        echarts.setSeriesData(objects);
        return echarts;
    }

    public Echarts userActiveRetained (Integer today) {
        Echarts echarts = new Echarts();
        echarts.setSeriesData(new ArrayList<Object>(0));
        /// 近三天
        switch (today) {
            case 1: // 近三天
                List<UserActiveRetainedStat> firstUserActiveRetainedStat = statDao.findUserActiveRetainedStatByGteAddTimeAndLte(DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, -3)).getTime(), DateUtil.getDayEnd(new Date()).getTime());
                echarts.setLegendData(Arrays.asList("前一天", "前两天", "前三天"));
                for (UserActiveRetainedStat userActiveRetainedStat : firstUserActiveRetainedStat) {
                    List<Object> data = new ArrayList<>();
                    data.add(userActiveRetainedStat.getOneTimeUser());
                    data.add(userActiveRetainedStat.getDayActiveUser());
                    data.add(userActiveRetainedStat.getWeekActiveUser());
                    data.add(userActiveRetainedStat.getMonthActiveUser());
                    data.add(userActiveRetainedStat.getDayRetained());
                    data.add(userActiveRetainedStat.getWeekRetained());
                    data.add(userActiveRetainedStat.getMonthRetained());
                    echarts.getSeriesData().add(data);
                }
                break;
        }
        return echarts;
    }


    ///
    ///	实时用户运动分布图

    public List<UserBehaviorLog> realTimeUserRunData(Long lastRunTime) {
        long beginTime;
        if (lastRunTime != null) {
            beginTime = lastRunTime;
        }
        else {
            beginTime = DateUtil.getDayBegin(new Date()).getTime();
        }
        long endTime = DateUtil.getDayEnd(new Date()).getTime();

        List<UserBehaviorLog> userBehaviorLogs = userBehaviorLogCoreDao.findWithAddTime(beginTime, endTime);
        if (!CollectionUtils.isEmpty(userBehaviorLogs)) {
            Set<Integer> userIds = CollectionUtil.buildSet(userBehaviorLogs, Integer.class, "uid");
            Map<Integer, User> userMap = CollectionUtil.buildMap(userCoreDao.findUserById(userIds, null, "id", "name"), Integer.class, User.class, "id");
            for (UserBehaviorLog userBehaviorLog : userBehaviorLogs) {
                userBehaviorLog.setUser(userMap.get(userBehaviorLog.getUid()));
            }
        }
        return userBehaviorLogs;
    }


    ///
    /// 用户分渠道注册用户

    /**
     *  用户分渠道注册用户
     * @param beginTime   开始时间
     * @param endTime   结束时间
     * @return 0、Echarts 1、UserRegisterChannelStat 2、UserRegisterTypeStat
     */
    public Object[] registerChannelStatData(Long beginTime, Long endTime) {

        /// 获取数据源
        List<UserRegisterChannelStat> userRegisterChannelStatList = statDao.findUserRegisterChannelStatByAddTime(beginTime, endTime);
        Set<ObjectId> objectIdSet = new HashSet<>();
        for (UserRegisterChannelStat userRegisterChannelStat : userRegisterChannelStatList) {
            objectIdSet.addAll(userRegisterChannelStat.getUserRegisterTypeStatId());
        }
        List<UserRegisterTypeStat> userRegisterTypeStatList = statDao.findUserRegisterTypeStatByIds(objectIdSet);
        List<Dictionary> dictionaryList = DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL); // 渠道 dictionary
        Map<Integer, Dictionary> dictionaryValueMap = CollectionUtil.buildMap(dictionaryList, Integer.class, Dictionary.class, "value"); // 渠道 dictionary 转 map

        /// 处理 Echarts LegendData

        for (UserRegisterChannelStat userRegisterChannelStat : userRegisterChannelStatList) {
            Dictionary dictionary = null;
            if (null != userRegisterChannelStat.getChannel()) {
                // 需要额外处理 appStore
                if (NumberUtils.isNumber(userRegisterChannelStat.getChannel())) {
                    dictionary = dictionaryValueMap.get(Integer.valueOf(userRegisterChannelStat.getChannel()));
                }
                else if (DicConstants.DIC_VALUE_CHANNEL_APP_STORE.equals(userRegisterChannelStat.getChannel())) {
                    dictionary = new Dictionary();
                    dictionary.setName(DicConstants.DIC_VALUE_CHANNEL_APP_STORE);
                }
            }
            if (dictionary != null) {
                userRegisterChannelStat.setChannelName(dictionary.getName());
            }
        }
        Set<String> legendDataList = CollectionUtil.buildSet(userRegisterChannelStatList, String.class, "channelName");

        List seriesDataList = CollectionUtil.buildList(userRegisterChannelStatList, Integer.class, "loginCount"); // 新用户注册 集合

        /// 构建 Echarts 数据
        Echarts echarts = new Echarts();
        echarts.setLegendData(legendDataList);
        echarts.setSeriesData(seriesDataList);

        return new Object[]{echarts, userRegisterChannelStatList, userRegisterTypeStatList};
    }

    /**
     * 体验用户 和 注册用户统计
     * @return Echarts 图表对象
     */
    public Echarts userExperienceAndUserData(Integer type) {

        Long beginAddTime, endAddTime;
        List<Object> seriesData = new ArrayList<>();
        List<String> xAxisData = new ArrayList<>();
        switch (type) {
            case 1: // 日。不包括今天
                beginAddTime = DateUtil.addDate(Calendar.DAY_OF_YEAR, -6).getTime();
                endAddTime = DateUtil.addDate(Calendar.DAY_OF_YEAR, -1).getTime();
                /// 体验用户 和 注册用户统计 集合
                List<UserExperienceAndUserStat> dayUserExperienceAndUserStatList = statDao.findUserExperienceAndUserStatByAddTime(beginAddTime, endAddTime);

                seriesData.add(CollectionUtil.buildList(dayUserExperienceAndUserStatList, Integer.class, "totalCount"));
                seriesData.add(CollectionUtil.buildList(dayUserExperienceAndUserStatList, Integer.class, "registerCount"));
                seriesData.add(CollectionUtil.buildList(dayUserExperienceAndUserStatList, Integer.class, "experienceCount"));
                xAxisData = CollectionUtil.buildList(dayUserExperienceAndUserStatList, String.class, "addTimeStr");
                break;
            case 2: // 包括本周
                // 总容器
                List<Double> weekTotalCountList = new ArrayList<>();
                List<Double> weekRegisterCountList = new ArrayList<>();
                List<Double> weekExperienceCountList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Date week = DateUtil.addDate(Calendar.WEEK_OF_YEAR, -i);
                    Date weekBegin = DateUtil.getDayBegin(DateUtil.getWeekBegin(week));
                    Date weekEnd = DateUtil.getDayEnd(DateUtil.getWeekEnd(week));
                    List<UserExperienceAndUserStat> weekUserExperienceAndUserStatList = statDao.findUserExperienceAndUserStatByAddTime(weekBegin.getTime(), weekEnd.getTime());

                    Map<String, Number> weekPropertyCount = CollectionUtil.buildPropertyCount(weekUserExperienceAndUserStatList, "totalCount", "registerCount", "experienceCount");
                    // 添加 总记录
                    weekTotalCountList.add(weekPropertyCount.get("totalCount").doubleValue());
                    weekRegisterCountList.add(weekPropertyCount.get("registerCount").doubleValue());
                    weekExperienceCountList.add(weekPropertyCount.get("experienceCount").doubleValue());
                    xAxisData.add(DateUtil.format(weekBegin, "yyyy-MM-dd") + " ~ " + DateUtil.format(weekEnd, "yyyy-MM-dd"));
                }
                seriesData.add(weekTotalCountList);
                seriesData.add(weekRegisterCountList);
                seriesData.add(weekExperienceCountList);
                break;
            case 3: // 包括本周
                // 总容器
                List<Double> monthTotalCountList = new ArrayList<>();
                List<Double> monthRegisterCountList = new ArrayList<>();
                List<Double> monthExperienceCountList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Date week = DateUtil.addDate(Calendar.MONTH, -i);
                    Date weekBegin = DateUtil.getDayBegin(DateUtil.getMonthBegin(week));
                    Date weekEnd = DateUtil.getDayEnd(DateUtil.getMonthEnd(week));
                    List<UserExperienceAndUserStat> weekUserExperienceAndUserStatList = statDao.findUserExperienceAndUserStatByAddTime(weekBegin.getTime(), weekEnd.getTime());
                    Map<String, Number> weekPropertyCount = CollectionUtil.buildPropertyCount(weekUserExperienceAndUserStatList, "totalCount", "registerCount", "experienceCount");
                    // 添加 总记录
                    monthTotalCountList.add(weekPropertyCount.get("totalCount").doubleValue());
                    monthRegisterCountList.add(weekPropertyCount.get("registerCount").doubleValue());
                    monthExperienceCountList.add(weekPropertyCount.get("experienceCount").doubleValue());
                    xAxisData.add(DateUtil.format(weekBegin, "yyyy-MM-dd") + " ~ " + DateUtil.format(weekEnd, "yyyy-MM-dd"));
                }
                seriesData.add(monthTotalCountList);
                seriesData.add(monthRegisterCountList);
                seriesData.add(monthExperienceCountList);
                break;
        }

        Echarts echarts = new Echarts();
        echarts.setLegendData(Arrays.asList("总用户", "注册用户", "体验用户"));
        echarts.setSeriesData(seriesData);
        echarts.setxAxisData(xAxisData);

        return echarts;
    }

    public Echarts userExperienceAndUserAppendData(Integer type) {
        Long beginAddTime, endAddTime;
        List<Object> seriesData = new ArrayList<>();
        List<String> xAxisData = new ArrayList<>();
        switch (type) {
            case 1: // 日。不包括今天
                beginAddTime = DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, -6)).getTime();
                endAddTime = DateUtil.getDayEnd(DateUtil.addDate(Calendar.DAY_OF_YEAR, -1)).getTime();

                /// 体验用户 和 注册用户统计 集合
                List<UserExperienceAndUserStat> dayUserExperienceAndUserStatList = statDao.findUserExperienceAndUserStatByAddTime(beginAddTime, endAddTime);

                seriesData.add(CollectionUtil.buildList(dayUserExperienceAndUserStatList, Integer.class, "appendTotalCount"));
                seriesData.add(CollectionUtil.buildList(dayUserExperienceAndUserStatList, Integer.class, "appendRegisterCount"));
                seriesData.add(CollectionUtil.buildList(dayUserExperienceAndUserStatList, Integer.class, "appendExperienceCount"));
                xAxisData = CollectionUtil.buildList(dayUserExperienceAndUserStatList, String.class, "addTimeStr");
                break;
            case 2: // 包括本周
                // 总容器
                List<Double> weekTotalCountList = new ArrayList<>();
                List<Double> weekRegisterCountList = new ArrayList<>();
                List<Double> weekExperienceCountList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Date week = DateUtil.addDate(Calendar.WEEK_OF_YEAR, -i);
                    Date weekBegin = DateUtil.getDayBegin(DateUtil.getWeekBegin(week));
                    Date weekEnd = DateUtil.getDayEnd(DateUtil.getWeekEnd(week));
                    List<UserExperienceAndUserStat> weekUserExperienceAndUserStatList = statDao.findUserExperienceAndUserStatByAddTime(weekBegin.getTime(), weekEnd.getTime());
                    Map<String, Number> weekPropertyCount = CollectionUtil.buildPropertyCount(weekUserExperienceAndUserStatList, "appendTotalCount", "appendRegisterCount", "appendExperienceCount");
                    // 添加 总记录
                    weekTotalCountList.add(weekPropertyCount.get("appendTotalCount").doubleValue());
                    weekRegisterCountList.add(weekPropertyCount.get("appendRegisterCount").doubleValue());
                    weekExperienceCountList.add(weekPropertyCount.get("appendExperienceCount").doubleValue());
                    xAxisData.add(DateUtil.format(weekBegin, "yyyy-MM-dd") + " ~ " + DateUtil.format(weekEnd, "yyyy-MM-dd"));
                }
                seriesData.add(weekTotalCountList);
                seriesData.add(weekRegisterCountList);
                seriesData.add(weekExperienceCountList);
                break;
            case 3: // 包括本周
                // 总容器
                List<Double> monthTotalCountList = new ArrayList<>();
                List<Double> monthRegisterCountList = new ArrayList<>();
                List<Double> monthExperienceCountList = new ArrayList<>();
                for (int i = 0; i < 5; i++) {
                    Date week = DateUtil.addDate(Calendar.MONTH, -i);
                    Date weekBegin = DateUtil.getDayBegin(DateUtil.getMonthBegin(week));
                    Date weekEnd = DateUtil.getDayEnd(DateUtil.getMonthEnd(week));
                    List<UserExperienceAndUserStat> weekUserExperienceAndUserStatList = statDao.findUserExperienceAndUserStatByAddTime(weekBegin.getTime(), weekEnd.getTime());
                    Map<String, Number> weekPropertyCount = CollectionUtil.buildPropertyCount(weekUserExperienceAndUserStatList, "appendTotalCount", "appendRegisterCount", "appendExperienceCount");
                    // 添加 总记录
                    monthTotalCountList.add(weekPropertyCount.get("appendTotalCount").doubleValue());
                    monthRegisterCountList.add(weekPropertyCount.get("appendRegisterCount").doubleValue());
                    monthExperienceCountList.add(weekPropertyCount.get("appendExperienceCount").doubleValue());
                    xAxisData.add(DateUtil.format(weekBegin, "yyyy-MM-dd") + " ~ " + DateUtil.format(weekEnd, "yyyy-MM-dd"));
                }
                seriesData.add(monthTotalCountList);
                seriesData.add(monthRegisterCountList);
                seriesData.add(monthExperienceCountList);
                break;
        }

        Echarts echarts = new Echarts();
        echarts.setLegendData(Arrays.asList("总用户", "总注册用户", "总体验用户"));
        echarts.setSeriesData(seriesData);
        echarts.setxAxisData(xAxisData);
        return echarts;
    }

    public Map<String, Integer> totalUserRunNum(String bTime, String eTime) {
        Date begin = DateUtil.parse(bTime, "yyyy-MM-dd");
        Date end = DateUtil.parse(eTime, "yyyy-MM-dd");
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Map<String, Integer> maps = new HashMap<>();
        while (begin.compareTo(end) <= 0) {
            Long beginTime = begin.getTime();
            Long size = defaultDao.count(Query.query(Criteria.where("startTime").gte(beginTime).lte(beginTime + 86400000)), UserRun.class);
            maps.put(DateUtil.format(begin, "yyyy-MM-dd"), Integer.parseInt(size + ""));
            begin = DateUtil.getNextDay(begin);
        }
        return maps;
    }

    /**
     * 城市运动 统计计划
     *
     * @param page 分页对象
     */
    public void managerCityRunStat(Page<SnapTable> page) {
        statDao.cityRunStat(page);
    }

    /**
     * 城市运动 统计明细
     *
     * @param page 分页对象
     */
    public void cityRunStatInfo(Page<SnapTable> page) {
        statDao.cityRunStat(page);

        List<SnapTable> stats = page.getResult();
        if (!CollectionUtils.isEmpty(stats)) {
            for (SnapTable s : stats) {
                Map<String, Object> values = s.getValues();
                Integer uid = Integer.parseInt(values.get("uid")+"");
                User user = userDao.findUserByUid(uid, "mobile", "email");
                values.put("mobile", user.getMobile());
                values.put("email", user.getEmail());
            }
        }
    }

    /**
     * 城市运动 删除计划
     *
     * @param planId 计划编号
     */
    public void cityRunStatDelete(Long planId) {
        statDao.removeSnap(planId);
        statDao.removeSnapByPlanId(planId);
    }

    public void removeRunStat() {
        statDao.removeRunStat();
    }

    public void statRepeatUserDelete() {
        statDao.statRepeatUserDelete();
    }

    public void deleteSnapTableById(Long id) {
        statDao.deleteSnapTableById(id);
    }

    public void deleteSnapTableByMobile(String mobile, Set<Integer> uids) {
        statDao.deleteSnapTableByMobile(mobile, uids);
    }


    public void deleteSnapTableByEmail(String email, Set<Integer> uids) {
        statDao.deleteSnapTableByEmail(email, uids);
    }
}
