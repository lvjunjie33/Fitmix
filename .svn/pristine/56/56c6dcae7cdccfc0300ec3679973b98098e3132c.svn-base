package com.business.work.stat;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.DicConstants;
import com.business.core.entity.Page;
import com.business.core.entity.address.Position;
import com.business.core.entity.logs.SnapTable;
import com.business.core.entity.stat.MapRunSearchStat;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.info.UserRunStat;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.*;
import com.business.work.base.support.BaseControllerSupport;
import com.business.work.userRun.UserRunService;
import com.google.common.collect.ImmutableList;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletResponse;
import java.util.*;

/**
 * Created by Administrator on 2015/6/9 0009.
 */
@Controller
@RequestMapping()
public class StatController extends BaseControllerSupport{

    private static Logger logger = LoggerFactory.getLogger(StatController.class);

    @Autowired
    private StatService statService;
    @Autowired
    DefaultDao defaultDao;
    @Autowired
    private UserRunService userRunService;

    /**
     * 用户统计
     * @param model
     * @param beginTime
     * @param endTime
     * @return
     */
    @RequestMapping("/stat/user-stat")
    public String userStat (Model model, Long beginTime, Long endTime) {
        if (beginTime == null) {
            beginTime = DateUtil.getDayBegin(DateUtil.addDate(Calendar.DAY_OF_YEAR, -2)).getTime();
        }
        if (endTime == null) {
            endTime = DateUtil.getDayEnd(DateUtil.addDate(Calendar.DAY_OF_YEAR, 0)).getTime();
        }
        Object[] objects = statService.userStat(beginTime, endTime);
        model.addAttribute("userGrowthLossList", objects[0]);
        model.addAttribute("userActiveRetainedList", objects[1]);
        return "stat/user-stat";
    }

    /**
     * 时段分析
     * @param today
     * @param data
     */
    @RequestMapping("/stat/user-growth-loss-stat")
    public void userGrowthLossStat (Model model,
                                    @RequestParam("today") Integer today,
                                    @RequestParam("data") Integer data) {
        model.addAttribute("echarts", statService.userGrowthLossStat(today, data));
    }

    /**
     *
     * @param today
     */
    @RequestMapping("/stat/user-active-retained")
    public void userActiveRetained (Model model,
                                    @RequestParam("today") Integer today) {
        model.addAttribute("echarts", statService.userActiveRetained(today));
    }


    ///
    ///	实时用户运动分布图

    /**
     * 1、	APP在用户听音乐和开始运动，向后台上报“听音乐”、“跑步”事件，并带上坐标；
     * 2、	后台统计显示页面，实时显示最新50条记录，最新记录在最上面；
     * 3、	“开始\暂停”按钮，当按下“开始”，记录列表开始实时刷新，按下”暂停”，记录列表停止刷新，以便选择每条记录；
     * 4、	选择一条记录，在左侧地图界面上定位并显示该位置信息。
     */

    @RequestMapping(value = "/stat/real-time-user-run-data", method = RequestMethod.GET)
    public String realTimeUserRunData () {
        return "stat/real-time-user-run-data-stat";
    }

    @RequestMapping(value = "/stat/real-time-user-run-data", method = RequestMethod.POST)
    public void realTimeUserRunData (Model model, Long lastRunTime) {
        model.addAttribute("data", statService.realTimeUserRunData(lastRunTime));
    }

    ///
    /// 用户分渠道注册用户

    /**
     *
     * 需求来源：运营要统计分析注册用户的渠道来源，以判断哪个渠道质量好；
     * 1、APP用户注册时上报渠道号；
     * 2、后台的用户统计页面，可按： 注册时间段、渠道号、注册类型（自注册账号、第三方账号-QQ、微信、微博），输出图表。
     */

    @RequestMapping("/stat/register-channel-stat")
    public String registerChannelStat(Model model) {
        model.addAttribute("currentDate", DateUtil.format(new Date(), "yyyy-MM-dd"));
        model.addAttribute("channelDictionary", JSON.toJSONString(DicUtil.getDictionary(DicConstants.DIC_TYPE_CHANNEL)));
        return "stat/register-channel-stat";
    }

    @RequestMapping(value = "/stat/register-channel-stat-data")
    public void registerChannelStatData (Model model,
                                         @RequestParam(value = "beginTime", required = false) String beginTime,
                                         @RequestParam(value = "endTime", required = false) String endTime) {
        Long buildBeginTime;
        Long buildEndTime;
        if (StringUtils.isEmpty(beginTime)) {
            buildBeginTime = DateUtil.getDayBegin(new Date()).getTime();
            buildEndTime = DateUtil.getDayEnd(new Date()).getTime();
        }
        else {
            buildBeginTime = DateUtil.getDayBegin(DateUtil.parse(beginTime, "yyyy-MM-dd")).getTime();
            buildEndTime = DateUtil.getDayEnd(DateUtil.parse(endTime, "yyyy-MM-dd")).getTime();
        }
        Object[] result = statService.registerChannelStatData(buildBeginTime, buildEndTime);
        model.addAttribute("echarts", result[0]);
        model.addAttribute("registerChannel", result[1]);
        model.addAttribute("registerType", result[2]);
    }


    ///
    /// 体验用户 和 注册用户统计

    /**
     * 总新增用户、新增注册用户、新增游客用户，做折线图，图可以按照日、周、月变换
     */
    @RequestMapping("/stat/user-experience-and-user-stat")
    public String userExperienceAndUserStat() {
        return "stat/user-experience-and-user-stat";
    }


    /**
     * 时间筛选，天 周 月
     * 1、近5日     2、近5周     3、近5月
     */
    @RequestMapping("/stat/user-experience-and-user-data")
    public void userExperienceAndUserData(Model model, @RequestParam(value = "type", required = false, defaultValue = "1") Integer type) {
        model.addAttribute("echarts", statService.userExperienceAndUserData(type));
    }

    /**
     * 时间筛选，天 周 月
     * 1、近5日     2、近5周     3、近5月
     */
    @RequestMapping("/stat/user-experience-and-user-append-data")
    public void userExperienceAndUserAppendData(Model model, @RequestParam(value = "type", required = false, defaultValue = "1") Integer type) {
        model.addAttribute("echarts", statService.userExperienceAndUserAppendData(type));
    }

    /**
     * 合计用户运动总里程
     *
     * @param page 分页对象
     */
    @RequestMapping("/stat/total-user-run")
    public String totalUserRun(Page<UserRunStat> page) {
        page.removeEmptys("minDistance", "maxDistance").convertLong("minDistance").convertLong("maxDistance");

        Integer pageNo = page.getPageNo();
        //默认每页10条
        page.setSize(30);

        Map<String, Object> filter = page.getFilter();

        if (!filter.containsKey("minDistance") && !filter.containsKey("maxDistance")) {
            return "stat/total-user-run";
        }

        filter.put("type", UserRunStat.STAT_SUM);
        filter.put("statTime", "--");

        userRunService.runMonthRank(page);
        List<UserRunStat> userRunStats = page.getResult();
        for (int i = 0; i < userRunStats.size(); i++) {
            userRunStats.get(i).setRank(i * pageNo + 1L);
        }
        return "stat/total-user-run";
    }

    /**
     * 通过时间-统计用户当日总运动量
     *
     * @return
     */
    @RequestMapping("/stat/total-user-run-num")
    public void totalUserRunNum(Model model, String bTime, String eTime) {
        model.addAttribute("stats", statService.totalUserRunNum(bTime, eTime));
    }

    @RequestMapping("/stat/collect/user/run")
    public void collectUserRun() {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        List<UserRun> userRuns = defaultDao.query(Query.query(Criteria.where("id").is(2705550)), UserRun.class);
        for (UserRun userRun : userRuns) {
            try {
                String link = FileCenterClient.buildUrl(userRun.getDetail());
                String target = HttpUtil.get(link);

//                Map<String, List<List<List<Object>>>> map = (Map<String, List<List<List<Object>>>>) JSON.parse(target);
                JSONObject jsonObject = (JSONObject) JSON.parse(target);
                JSONArray jsonArray = (JSONArray) jsonObject.get("array");
                List<Position> tracks = new ArrayList<>();
                for (Object object : jsonArray) {
                    JSONObject json = (JSONObject) object;
                    Double lng = Double.parseDouble(json.get("lng").toString());
                    Double lat = Double.parseDouble(json.get("lat").toString());
                    tracks.add(Position.buildPosition(lng, lat, null));
                }

                MapRunSearchStat searchStat = new MapRunSearchStat();
                User user = defaultDao.findById(User.class, userRun.getUid(), "name", "avatar");
                searchStat.setUserName(user.getName());
                searchStat.setAvatar(user.getAvatar());;
                searchStat.setUserId(user.getId());

                searchStat.setBpm(userRun.getBpm());
                searchStat.setCalorie(userRun.getCalorie());
                searchStat.setDistance(userRun.getDistance());
                searchStat.setStep(userRun.getStep());
                searchStat.setUserRunId(userRun.getId());

                searchStat.setTracks(tracks);
                searchStat.setOutset(tracks.get(0));
                searchStat.setFinish(tracks.get(tracks.size() - 1));

                defaultDao.save(searchStat);
            }catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //=========================================指定城市、指定月份、总里程大于50KM begin===============================================

    /**
     * 管理
     *
     * @param page 分页对象
     */
    @RequestMapping("/city/run/stat/manager")
    public String cityRunStatManager(Page<SnapTable> page) {
        page.removeEmpty("city");
        page.getFilter().put("type", SnapTable.TYPE_USER_RUN_PLAN);
        statService.managerCityRunStat(page);
        return "stat/user-run-stat";
    }

    /**
     * 添加统计计划
     *
     * @param city 城市
     * @param time 时间
     * @param distance 里程
     */
    @RequestMapping("/city/run/stat/add")
    public void cityRunStatAdd(Model model, String city, String time, Integer distance) {

        SnapTable oldSnapTable = defaultDao.findOne(Query.query(Criteria.where("values.city").is(city).and("values.time").is(time).and("values.distance").is(distance).and("type").is(SnapTable.TYPE_USER_RUN_PLAN)), SnapTable.class);
        if (oldSnapTable != null) {
            tip(model, -1, "该统计已经存在");
            return;
        }
        SnapTable snapTable = new SnapTable();
        snapTable.setType(SnapTable.TYPE_USER_RUN_PLAN);
        snapTable.setAddTime(System.currentTimeMillis());

        Map<String, Object> values = new HashMap<>();
        values.put("city", city);
        values.put("time", time);
        values.put("distance", distance);
        values.put("status", "prepare");//prepare, start, finish
        snapTable.setValues(values);
        defaultDao.save(snapTable);
    }

    /**
     * 开始计划
     *
     * @param planId 计划编号
     */
    @RequestMapping("/city/run/stat/start")
    public void cityRunStatStart(Model model, Long planId) {
        SnapTable snapTable = null;
        synchronized (planId) {
            Long num = defaultDao.count(Query.query(Criteria.where("values.status").is("start").and("type").is(SnapTable.TYPE_USER_RUN_PLAN)), SnapTable.class);
            if (num > 0) {
                tip(model, -1, "目前有计划正在执行。。。");
                return;
            }
            snapTable = defaultDao.findOne(Query.query(Criteria.where("id").is(planId).and("type").is(SnapTable.TYPE_USER_RUN_PLAN)), SnapTable.class);
            if (snapTable == null) {
                tip(model, -1, "该计划不存在。。。");
                return;
            }
            defaultDao.modifyFirst(Query.query(Criteria.where("id").is(planId)), Update.update("values.status", "start"), SnapTable.class);
        }

        Map<String, Object> values = snapTable.getValues();
        String city = values.get("city").toString();
        Integer distance = Integer.parseInt(values.get("distance").toString());
        Date monthTime = DateUtil.parse(values.get("time").toString(), "yyyy-MM");
        Date monthBegin = DateUtil.getMonthBegin(monthTime);
        Date monthEnd = DateUtil.getMonthEnd(monthTime);

        Criteria c1 = Criteria.where("registerTaoBaoIp.city").is(city).and("isNew").is(null);
        Criteria c2 = Criteria.where("taoBaoIp.city").is(city);

        List<User> users = defaultDao.query(Query.query(new Criteria().orOperator(c1, c2)), User.class, "name");

        Long currentDate = System.currentTimeMillis();
        for (User user : users) {
            logger.error("user id:" + user.getId());
            List<UserRun> userRuns = defaultDao.query(Query.query(Criteria.where("uid").is(user.getId()).and("addTime").gte(monthBegin.getTime()).lte(monthEnd.getTime())),
                    UserRun.class, "distance", "runTime", "consumeFat", "step", "calorie");
            Long distanceSum = 0L;
            Long stepSum = 0L;
            Long calorieSum = 0L;
            Double consumeFatSum = 0D;
            Integer runSum = 0;
            Long runTimeSum = 0L;
            for (UserRun userRun : userRuns) {
                handleUserRun(userRun);
                distanceSum += userRun.getDistance();
                runTimeSum += userRun.getRunTime();
                stepSum += userRun.getStep();
                calorieSum += userRun.getCalorie();
                consumeFatSum += userRun.getConsumeFat();
                runSum++;
            }
            if (distanceSum < distance) {
                continue;
            }
            SnapTable stat = new SnapTable();
            stat.setAddTime(currentDate);
            stat.setType(SnapTable.TYPE_USER_RUN_CITY_STAT);
            Map<String, Object> map = new HashMap<>();
            map.put("distanceSum", distanceSum);
            map.put("rumSum", runSum);
            map.put("runTimeSum", runTimeSum);
            map.put("uid", user.getId());
            map.put("name", user.getName());
            map.put("planId", snapTable.getId());
            map.put("stepSum", stepSum);
            map.put("calorieSum", calorieSum);
            map.put("consumeFatSum", consumeFatSum);
            map.put("pace", RunUtil.pace(distanceSum, runTimeSum));

            stat.setValues(map);
            defaultDao.save(stat);
        }

//        String[] cityBJ = {"北京市"};
//        String[] citySZ = {"深圳市", "广州市"};
//        String[] citySH = {"上海市"};
//        String[] cityOther = {"杭州市","成都市", "南京市", "西安市", "石家庄市", "郑州市"};

        defaultDao.modifyFirst(Query.query(Criteria.where("id").is(planId)), Update.update("values.status", "finish"), SnapTable.class);
    }

    private void handleUserRun(UserRun run) {
        if (run.getDistance() == null) {
            run.setDistance(0L);
        }
        if (run.getRunTime() == null) {
            run.setRunTime(0L);
        }
        if (run.getCalorie() == null) {
            run.setCalorie(0L);
        }
        if (run.getConsumeFat() == null) {
            run.setConsumeFat(0D);
        }
        if (run.getStep() == null) {
            run.setStep(0L);
        }
    }

    /**
     * 城市统计明细
     *
     * @param page 分页对象
     */
    @RequestMapping("/city/run/stat/info")
    public String cityRunStatInfo(Model model, Page<SnapTable> page) {
        page.removeEmpty("planId").convertLong("planId");
        page.getFilter().put("type", SnapTable.TYPE_USER_RUN_CITY_STAT);
        Long planId = Long.parseLong(page.getFilter().get("planId").toString());
        statService.cityRunStatInfo(page);
        model.addAttribute("plan", defaultDao.findOne(Query.query(Criteria.where("id").is(planId).and("type").is(SnapTable.TYPE_USER_RUN_PLAN)), SnapTable.class));
        return "stat/user-run-stat-info";
    }

    /**
     * 用户分页 （导出）
     */
    @RequestMapping("/city/run/stat/info/export")
    public void userPageExport(HttpServletResponse response, Long planId) {

        SnapTable plan = defaultDao.findOne(Query.query(Criteria.where("id").is(planId).and("type").is(SnapTable.TYPE_USER_RUN_PLAN)), SnapTable.class);

        List<SnapTable> stats = defaultDao.query(Query.query(Criteria.where("values.planId").is(planId)), SnapTable.class);
        Map<Integer, SnapTable> map = new HashMap<>();
        for(SnapTable snap : stats) {
            Map<String, Object> values = snap.getValues();
            Integer uid = Integer.parseInt(values.get("uid") +"");
            map.put(uid, snap);
        }

        List<User> users = defaultDao.query(Query.query(Criteria.where("id").in(map.keySet())), User.class, "mobile", "email");
        for (User user : users) {
            String mobile = user.getMobile();
            String email = user.getEmail();
            if (mobile == null) {
                mobile = "";
            }
            if (email == null) {
                email = "";
            }
            map.get(user.getId()).getValues().put("mobile", mobile);
            map.get(user.getId()).getValues().put("email", email);
        }

        ImmutableList<Integer> columnWidths = ImmutableList.of(2000, 5000, 5000 ,5000, 5000);
        ImmutableList<String> columnNames = ImmutableList.of("序号", "用户名称/编号", "总里程", "总运动次数", "总运动时间(秒)", "总步数", "总卡路里", "总燃脂量", "平均配速", "手机号码", "邮箱");
        List<List<String>> list = new ArrayList<>();

        int index = 0;
        for (SnapTable stat: stats) {
            Map<String, Object> values = stat.getValues();

            list.add(
                    ImmutableList.of(
                            String.valueOf(++index),
                            values.get("uid") + "/" + values.get("name"),
                            values.get("distanceSum").toString(),
                            values.get("rumSum").toString(),
                            values.get("runTimeSum").toString(),
                            values.get("stepSum").toString(),
                            values.get("calorieSum").toString(),
                            values.get("consumeFatSum").toString(),
                            values.get("pace").toString(),
                            values.get("mobile").toString(),
                            values.get("email").toString())
            );
        }
        Double distance = 1.0 * Integer.parseInt(plan.getValues().get("distance").toString()) / 1000;
        HSSFWorkbook wb = OfficeUtil.buildExcel(plan.getValues().get("city") + "城市运动统计", columnWidths, columnNames, list);
        CommonUtil.downLoadExcel(response, plan.getValues().get("city") + "-" + distance +"KM", wb);
    }

    @RequestMapping("/city/run/stat/delete")
    public void cityRunStatDelete(Model model, Long planId) {
        SnapTable snapTable = defaultDao.findOne(Query.query(Criteria.where("id").is(planId).and("type").is(SnapTable.TYPE_USER_RUN_PLAN)), SnapTable.class);
        if ("start".equals(snapTable.getValues().get("status"))) {
            tip(model, -1, "目前有计划正在执行。。。");
        }
        statService.cityRunStatDelete(planId);
    }

    //=========================================指定城市、指定月份、总里程大于50KM end===============================================

    /**
     * 统计重复的邮箱手机号码帐号
     */
    @RequestMapping("stat/repeat/user")
    public void statRepeatUser(Integer bId, Integer eId) {
        for (; bId < eId; bId++) {
            logger.error("bUid" + bId);
            User user = defaultDao.findById(User.class, bId, "email", "mobile");
            if (user == null) {
                continue;
            }
            if (!StringUtils.isEmpty(user.getEmail())) {
                List<User> repeats = defaultDao.query(Query.query(Criteria.where("email").is(user.getEmail())), User.class, "name", "email", "mobile");
                handle1(bId, repeats, user.getEmail(), null);
            }

            if (!StringUtils.isEmpty(user.getMobile())) {
                List<User> repeats = defaultDao.query(Query.query(Criteria.where("mobile").is(user.getMobile())), User.class, "name", "email", "mobile");
                handle1(bId, repeats, null, user.getMobile());
            }

        }
    }

    private void handle1(Integer uid, List<User> repeats, String email, String mobile) {
        if (CollectionUtils.isEmpty(repeats)) {
            return;
        }
        if (repeats.size() == 1) {
            return;
        }
        for (User r : repeats) {
            if (uid.equals(r.getId())) {
                continue;
            }
            SnapTable snapTable = new SnapTable();
            snapTable.setType(SnapTable.TYPE_REPEAT_USER);
            Map<String, Object> map = new HashMap<>();
            if (!StringUtils.isEmpty(email)) {
                map.put("email", r.getEmail());
            }
            if (!StringUtils.isEmpty(mobile)) {
                map.put("mobile", r.getMobile());
            }
            map.put("uid", r.getId());
            snapTable.setValues(map);
            defaultDao.save(snapTable);
        }
    }

    /**
     * 删除 TYPE = 3 的任务
     */
    @RequestMapping("stat/repeat/user/delete")
    public void statRepeatUserDelete() {
        statService.statRepeatUserDelete();
    }

    /**
     * 重复的用户 列表
     */
    @RequestMapping("stat/repeat/user/list")
    public String statRepeatUserList(Model model, @RequestParam(required = false) String mobile, @RequestParam(required = false) String email, Integer bId, Integer limit) {
        if (StringUtils.isEmpty(bId) || StringUtils.isEmpty(limit)) {
            return "/stat/repeat-user";
        }
        Criteria criteria = Criteria.where("type").is(SnapTable.TYPE_REPEAT_USER);
        if (!StringUtils.isEmpty(mobile)) {
            criteria.and("values.mobile").is(mobile);
            model.addAttribute("mobile", mobile);
        }
        if (!StringUtils.isEmpty(email)) {
            criteria.and("values.email").is(email);
            model.addAttribute("email", email);
        }
        Query query = Query.query(criteria);
        query.with(new Sort(Sort.Direction.DESC, "values.uid"));
        query.limit(limit);
        query.skip(bId);
        List<SnapTable> snapTables = defaultDao.query(query, SnapTable.class);
        model.addAttribute("values", snapTables);
        model.addAttribute("bId", bId);
        model.addAttribute("limit", limit);
        return "/stat/repeat-user";
    }

    /**
     * 删除重复的任务
     */
    @RequestMapping("remove/repeat")
    public void removeRepeat() {
        long eId;
        long bId;
        {
            Query query = Query.query(Criteria.where("type").is(SnapTable.TYPE_REPEAT_USER));
            query.with(new Sort(Sort.Direction.ASC, "id"));
            SnapTable snapTable = defaultDao.findOne(query, SnapTable.class);
            bId = snapTable.getId();
        }
        {
            Query query = Query.query(Criteria.where("type").is(SnapTable.TYPE_REPEAT_USER));
            query.with(new Sort(Sort.Direction.DESC, "id"));
            SnapTable snapTable = defaultDao.findOne(query, SnapTable.class);
            eId = snapTable.getId();
        }

        for (; bId <= eId; bId++) {
            try {
                SnapTable snapTable = defaultDao.findById(SnapTable.class, bId);
                if (snapTable == null) {
                    continue;
                }
                Map<String, Object> map = snapTable.getValues();
                if (map.containsKey("uid")) {
                    Object o = map.get("uid");
                    if(!StringUtils.isEmpty(o)){
                        Long size = defaultDao.count(Query.query(Criteria.where("values.uid").is(Integer.parseInt(o.toString()))), SnapTable.class);
                        if (size > 1) {
                            System.out.println(o);
                            //删除
                            statService.deleteSnapTableById(snapTable.getId());
                        }
                    }
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }

    /**
     * 对任务进行处理
     */
    @RequestMapping("modify/repeat/user/all")
    public void modifyRepeatUser() {
        long eId;
        long bId;
        {
            Query query = Query.query(Criteria.where("type").is(SnapTable.TYPE_REPEAT_USER));
            query.with(new Sort(Sort.Direction.ASC, "id"));
            SnapTable snapTable = defaultDao.findOne(query, SnapTable.class);
            bId = snapTable.getId();
        }
        {
            Query query = Query.query(Criteria.where("type").is(SnapTable.TYPE_REPEAT_USER));
            query.with(new Sort(Sort.Direction.DESC, "id"));
            SnapTable snapTable = defaultDao.findOne(query, SnapTable.class);
            eId = snapTable.getId();
        }

        for (; bId <= eId; bId++) {
            try {
                SnapTable snapTable = defaultDao.findById(SnapTable.class, bId);
                if (snapTable == null) {
                    continue;
                }
                Map<String, Object> map = snapTable.getValues();
                {
                    Object o = map.get("mobile");
                    if (!StringUtils.isEmpty(o)) {
                        Map<Integer, Long> zeros = new HashMap<>();
                        Map<Integer, Long> sizes = new HashMap<>();
                        List<User> users = defaultDao.query(Query.query(Criteria.where("mobile").is(o.toString())), User.class, "mobile");
                        for (User user : users) {
                            Long size = defaultDao.count(Query.query(Criteria.where("uid").is(user.getId())), UserRun.class);
                            if (size == 0) {
                                zeros.put(user.getId(), size);
                            } else {
                                sizes.put(user.getId(), size);
                            }
                        }
                        if (sizes.size() > 0) {
                            for (Integer u : zeros.keySet()) {
                                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(u)), Update.update("mobile", o +"_1"), User.class);
                            }
                        } else {
                            Integer i = 0;
                            for (Integer u : zeros.keySet()) {
                                if (i == 0) {
                                    i++;
                                    continue;
                                }
                                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(u)), Update.update("mobile", o +"_1"), User.class);
                            }
                        }

                        statService.deleteSnapTableByMobile(o.toString(),sizes.keySet());
                    }
                }

                {
                    Object o = map.get("email");
                    if (!StringUtils.isEmpty(o)) {
                        Map<Integer, Long> zeros = new HashMap<>();
                        Map<Integer, Long> sizes = new HashMap<>();
                        List<User> users = defaultDao.query(Query.query(Criteria.where("email").is(o.toString())), User.class, "email");
                        for (User user : users) {
                            Long size = defaultDao.count(Query.query(Criteria.where("uid").is(user.getId())), UserRun.class);
                            if (size == 0) {
                                zeros.put(user.getId(), size);
                            } else {
                                sizes.put(user.getId(), size);
                            }
                        }
                        if (sizes.size() > 0) {
                            for (Integer u : zeros.keySet()) {
                                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(u)), Update.update("email", o +"_1"), User.class);
                            }
                        } else {
                            Integer i = 0;
                            for (Integer u : zeros.keySet()) {
                                if (i == 0) {
                                    i++;
                                    continue;
                                }
                                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(u)), Update.update("email", o +"_1"), User.class);
                            }
                        }

                        statService.deleteSnapTableByEmail(o.toString(),sizes.keySet());
                    }
                }
            } catch (Exception e) {
                System.out.println();
            }
        }
    }


    @RequestMapping("/user/email/null/handle")
    public void userEmailNullHandle() {
        Query query = Query.query(Criteria.where("email").is(""));
        defaultDao.modifyMore(query, new Update().unset("email"), User.class);
        System.out.println("");
    }

    @RequestMapping("/user/mobile/null/handle")
    public void userMobileNullHandle() {
        Query query = Query.query(Criteria.where("mobile").is(""));
        defaultDao.modifyMore(query, new Update().unset("mobile"), User.class);
        System.out.println("");
    }

    /**
     * 重复的处理结果 进行区分
     */
    @RequestMapping("/user/repeat/handle")
    public void userRepeatHandle() {
        int i = 0;
        {
            List<User> users = defaultDao.query(Query.query(Criteria.where("email").regex("_1")), User.class, "email");
            for (User u : users) {
                if (i++ > 10) {
                    i = 0;
                }
                int num = i;
                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(u.getId())), Update.update("email", u.getEmail() + num), User.class);
            }
        }
        {
            List<User> users = defaultDao.query(Query.query(Criteria.where("mobile").regex("_1")), User.class, "mobile");
            for (User u : users) {
                if (i++ > 10) {
                    i = 0;
                }
                int num = i;
                defaultDao.modifyFirst(Query.query(Criteria.where("id").is(u.getId())), Update.update("mobile", u.getEmail() + num), User.class);
            }
        }
    }

}
