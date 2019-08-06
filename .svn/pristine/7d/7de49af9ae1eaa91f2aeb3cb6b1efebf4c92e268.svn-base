package com.business.work.activity;

import com.business.core.client.AliyunCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.entity.Page;
import com.business.core.entity.activity.Activity;
import com.business.core.entity.activity.ActivityGroup;
import com.business.core.entity.activity.ActivitySignUp;
import com.business.core.entity.activity.ActivitySignUpMember;
import com.business.core.entity.payOrder.PayOrder;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.entity.user.User;
import com.business.core.operations.activity.ActivityCoreDao;
import com.business.core.operations.payOrder.PayOrderCoreDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import java.util.*;

/**
 * Created by sin on 2015/9/11.
 */
@Service
public class ActivityService {

    @Autowired
    private ActivityCoreDao activityCoreDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private PayOrderCoreDao payOrderCoreDao;
    @Autowired
    private UserCoreDao userCoreDao;

    /**
     * 活动列表
     *
     * @param page 分页
     * @return 活动
     */
    @RequestMapping("list")
    public void list(Page<Activity> page) {
        activityCoreDao.list(page);
    }

    /**
     * 活动添加
     *
     * @param themeName 主题名
     * @param themeImage 主题图片
     * @param signUpBeginTime 报名开始时间
     * @param signUpEndTime 报名结束时间
     * @param activityBeginTime 活动开始时间
     * @param activityEndTime 活动结束时间
     * @param activityMaxNumber 活动最大人数
     * @param activityFalseNumber 虚假人数
     * @param desc 描述
     * @param type 赛事活动类型
     */
    @RequestMapping("add")
    public int add(Double activityMoney, String themeName, MultipartFile themeImage,
                   String activityBeginTime, String activityEndTime, String signUpBeginTime, String signUpEndTime,
                   Integer activityMaxNumber, Integer activityFalseNumber, String desc, Integer type) {

        // 活动名重复
        if (activityCoreDao.findActivityByThemeName(themeName, "id") != null){
            return 1;
        }

        Date beginTime = DateUtil.parse(activityBeginTime, "yyyy-MM-dd HH:mm");
        Date endTime = DateUtil.parse(activityEndTime, "yyyy-MM-dd HH:mm");

        String imageUrl = "";
        if (themeImage != null) {
            imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_ACTIVITY_THEME_IMAGE, themeImage);
        }

        Activity activity = new Activity();

        activity.setActivityMoney(activityMoney);
        activity.setThemeName(themeName);
        activity.setThemeImage(imageUrl);
        activity.setActivityMaxNumber(activityMaxNumber);
        activity.setActivityFalseNumber(activityFalseNumber);
        activity.setSignUpBeginTime(DateUtil.parse(signUpBeginTime, "yyyy-MM-dd HH:mm").getTime());
        activity.setSignUpEndTime(DateUtil.parse(signUpEndTime, "yyyy-MM-dd HH:mm").getTime());
        activity.setActivityBeginTime(beginTime.getTime());
        activity.setActivityEndTime(endTime.getTime());
        activity.setDesc(desc);
        activity.setType(type);

        // default
        activity.setSort(0);
        activity.setRegisterCount(0);
        activity.setReleaseStatus(Activity.RELEASE_STATUS_WAIT_RELEASE);
        activity.setStatus(Activity.STATUS_NORMAL);
        activity.setAddTime(System.currentTimeMillis());

        if (Activity.TYPE_NORMAL == type) {
            activity.setSignUpMode(new ArrayList<>(Activity.SIGN_UP_MODE_ALL.keySet()));
        }

        activityCoreDao.insertActivity(activity);
        return 0;
    }

    /**
     * 获取一个活动信息
     *
     * @param activityId 活动编号
     * @return 活动信息
     */
    public Activity getActivity(int activityId) {
        return activityCoreDao.findActivityById(activityId);
    }

    public int modifyActivity(Integer activityId, String themeName, MultipartFile themeImage, String signUpBeginTime, String signUpEndTime, String activityBeginTime,
                              String activityEndTime, Integer activityMaxNumber, Integer activityFalseNumber, Integer type, String desc, Integer sort, Integer releaseStatus, Integer status) {

        Activity activity = activityCoreDao.findActivityByThemeName(themeName, "id", "type");
        // 活动名重复
        if (activity != null && !activity.getId().equals(activityId)){
            return 1;
        }

        Update update = Update.update("themeName", themeName).
                set("signUpBeginTime", DateUtil.parse(signUpBeginTime, "yyyy-MM-dd HH:mm").getTime()).
                set("signUpEndTime", DateUtil.parse(signUpEndTime, "yyyy-MM-dd HH:mm").getTime()).
                set("activityBeginTime", DateUtil.parse(activityBeginTime, "yyyy-MM-dd HH:mm").getTime()).
                set("activityEndTime", DateUtil.parse(activityEndTime, "yyyy-MM-dd HH:mm").getTime()).
                set("activityMaxNumber", activityMaxNumber).
                set("activityFalseNumber", activityFalseNumber).
                set("type", type).
                set("desc", desc).
                set("sort", sort).
                set("releaseStatus", releaseStatus).
                set("status", status);
        if (!activity.getType().equals(type)) {
            update.unset("signUpMode");
        }

        if (themeImage != null) {
            String imageUrl = AliyunCenterClient.putFile(FileConstants.FILE_TYPE_ACTIVITY_THEME_IMAGE, themeImage);
            update.set("themeImage", imageUrl);
        }

        activityCoreDao.updateActivityById(activityId, update);
        return 0;
    }

    /**
     * 添加活动内容
     *
     * @param activityId
     * @param htmlContent
     * @return
     */
    public int addActivityContent(Integer activityId, String htmlContent) {
        Activity activity = activityCoreDao.findActivityById(activityId);
        if (activity == null) {
            return -1;
        }
        activityCoreDao.updateActivityById(activityId, Update.update("activityContent", htmlContent));
        return 0;
    }

    /**
     * 获取活动内容
     *
     * @param activityId 活动编号
     * @return
     */
    public String getActivityContent(Integer activityId) {
        return activityCoreDao.findActivityById(activityId, "activityContent").getActivityContent();
    }

    /**
     * 添加活动组信息
     *
     * @param activityId 活动编号
     * @param groupName 组名称
     * @param needMoney 需要的金额
     * @param memberNumber 组的人数
     * @param desc 描述
     * @param memberNames 组的各个成员昵称
     */
    public void toAddGroup(Integer activityId, String groupName, Double needMoney, Integer memberNumber, String desc, List<String> memberNames) {
        ActivityGroup activityGroup = new ActivityGroup();
        activityGroup.setId(UUID.randomUUID().toString());
        activityGroup.setGroupName(groupName);
        activityGroup.setMemberNumber(memberNumber);
        activityGroup.setNeedMoney(needMoney);
        activityGroup.setDesc(desc);
        activityGroup.setMemberNames(memberNames);

        activityCoreDao.updateActivityById(activityId, new Update().push("groups", activityGroup));
    }

    /**
     * 删除活动的组
     *
     * @param activityId 活动编号
     * @param groupId 组编号
     */
    public void removeActivityGroup(Integer activityId, String groupId) {
        Activity activity = activityCoreDao.findActivityById(activityId, "groups");
        List<ActivityGroup> activityGroups = activity.getGroups();
        Iterator<ActivityGroup> iterator = activityGroups.iterator();

        while (iterator.hasNext()) {
            ActivityGroup group = iterator.next();
            if (groupId.equals(group.getId())) {
                iterator.remove();
            }
        }
        activityCoreDao.updateActivityById(activityId, new Update().set("groups", activityGroups));
    }

    /**
     * 查询活动的用户报名信息
     */
    public void findSignUpInfo(Page<ActivitySignUp> page) {
        activityDao.findActivitySignUpInfo(page);

        List<ActivitySignUp> activitySignUps = page.getResult();
        List<PayOrder> payOrders = payOrderCoreDao.findPayOrderByOrderNos(CollectionUtil.buildList(activitySignUps, String.class, "tradeNoPlatform"), "payState", "orderNo");

        Map<String, PayOrder> payOrderMap = CollectionUtil.buildMap(payOrders, String.class, PayOrder.class, "orderNo");

        List<ActivitySignUp> oldActivitySignUps = activityDao.findActivitySignUpInfoByMobile(CollectionUtil.buildList(activitySignUps, String.class, "mobilePhone"), "mobilePhone");
        Map<String, Integer> counts = CollectionUtil.buildCountMap(oldActivitySignUps, String.class, "mobilePhone");

        for (ActivitySignUp activitySignUp : activitySignUps) {
            String orderNo = activitySignUp.getTradeNoPlatform();

            if (counts.containsKey(activitySignUp.getMobilePhone())) {
                activitySignUp.setCount(counts.get(activitySignUp.getMobilePhone()));
            } else {//这种情况是不存在的
                activitySignUp.setCount(0);
            }

            if ((null != activitySignUp.getTradeStatus()  && PayOrder.PAY_STATE_SUCCESS == activitySignUp.getTradeStatus()) || ( null != activitySignUp.getNeedMoney() && activitySignUp.getNeedMoney() == 0)) {
                activitySignUp.setTradeStatus(PayOrder.PAY_STATE_SUCCESS);
                continue;
            }
            if (payOrderMap.containsKey(orderNo)) {
                activitySignUp.setTradeStatus(payOrderMap.get(orderNo).getPayState());
            } else {// 如果没找到支付信息，则认为没有进行过支付操作
                activitySignUp.setTradeStatus(PayOrder.PAY_STATE_CREATE);
            }
        }
    }

    /**
     * @param page 赛事报名分页信息
     */
    public void signUpExport2(Page<ActivitySignUp> page){
        Integer activityId = (int)page.getFilter().get("activityId");
        page.setSize((int)activityDao.findActivitySignUpCount(activityId));
        activityDao.findActivitySignUpInfo(page);

        List<ActivitySignUp> activitySignUps = page.getResult();
        List<PayOrder> payOrders = payOrderCoreDao.findPayOrderByOrderNos(CollectionUtil.buildList(activitySignUps, String.class, "tradeNoPlatform"), "payState", "orderNo");

        Map<String, PayOrder> payOrderMap = CollectionUtil.buildMap(payOrders, String.class, PayOrder.class, "orderNo");

        List<ActivitySignUp> oldActivitySignUps = activityDao.findActivitySignUpInfoByMobile(CollectionUtil.buildList(activitySignUps, String.class, "mobilePhone"), "mobilePhone");
        Map<String, Integer> counts = CollectionUtil.buildCountMap(oldActivitySignUps, String.class, "mobilePhone");

        for (ActivitySignUp activitySignUp : activitySignUps) {
            String orderNo = activitySignUp.getTradeNoPlatform();

            if (counts.containsKey(activitySignUp.getMobilePhone())) {
                activitySignUp.setCount(counts.get(activitySignUp.getMobilePhone()));
            } else {//这种情况是不存在的
                activitySignUp.setCount(0);
            }

            if ((null != activitySignUp.getTradeStatus()  && PayOrder.PAY_STATE_SUCCESS == activitySignUp.getTradeStatus()) || ( null != activitySignUp.getNeedMoney() && activitySignUp.getNeedMoney() == 0)) {
                activitySignUp.setTradeStatus(PayOrder.PAY_STATE_SUCCESS);
                continue;
            }
            if (payOrderMap.containsKey(orderNo)) {
                activitySignUp.setTradeStatus(payOrderMap.get(orderNo).getPayState());
            } else {// 如果没找到支付信息，则认为没有进行过支付操作
                activitySignUp.setTradeStatus(PayOrder.PAY_STATE_CREATE);
            }
        }
    }

    /**
     * @param page 赛事报名分页信息
     */
    public List<List<String>> signUpExport(Page<ActivitySignUp> page){
        Integer activityId = (int)page.getFilter().get("activityId");
        page.setSize((int)activityDao.findActivitySignUpCount(activityId));

        activityDao.findActivitySignUpInfo(page);

        List<ActivitySignUp> activitySignUps = page.getResult();

        List<List<String>> exportData = new ArrayList<>();
        for (ActivitySignUp signUp : activitySignUps) {
            List<String> row = new ArrayList<>();
            row.add(signUp.getMobilePhone());
            row.add(signUp.getMobileName());
            row.add(signUp.getGroupName());
            row.add(DateUtil.format(DateUtil.parse(signUp.getAddTime()), "yyyy-MM-dd HH:mm:ss"));
            switch (signUp.getTradeStatus()){
                case PayOrder.PAY_STATE_SUCCESS:
                    row.add("支付成功");
                    break;
                case PayOrder.PAY_STATE_WAIT:
                    row.add("等待支付");
                    break;
                case PayOrder.PAY_STATE_CREATE:
                    row.add("创建订单");
                    break;
                case PayOrder.PAY_STATE_ERROR:
                    row.add("支付异常");
                    break;
            }
            String infos = "";
            for (ActivitySignUpMember member : signUp.getActivitySignUpMembers()) {
                String info = member.getGroupMemberName();
                if (!StringUtils.isEmpty(member.getName())) {
                    info += ":" + member.getName();
                }
                if (!StringUtils.isEmpty(member.getIdCard())) {
                    info += ":" + member.getIdCard();
                }
                if (!StringUtils.isEmpty(member.getGender())) {
                    info += ":" + member.getGender();
                }
                if (!StringUtils.isEmpty(member.getClothesSize())) {
                    info += ":" + member.getClothesSize();
                }
                if (!StringUtils.isEmpty(member.getMobile())) {
                    info += ":" + member.getMobile();
                }
                infos += info + "\n";
            }
            row.add(infos);
            row.add(signUp.getEmergencyPhone());
            row.add(signUp.getEmergencyName());
            exportData.add(row);
        }
        return exportData;
    }

    /**
     * 导出积分赛事排名
     *
     * @param activityId 赛事编号
     */
    public List<List<String>> signUpExport(Integer activityId) {
        Activity activity = activityCoreDao.findActivityById(activityId, "themeName", "activityEndTime");

        Date before = DateUtil.getDayBefore(new Date());
        Integer statDate = Integer.parseInt(DateUtil.format(before, "yyyyMMdd"));

        List<ActivityIntegralStat> stats = activityDao.findActivityIntegralStat(activityId, statDate);

        if (activity.getActivityEndTime() <= before.getTime() ) {
            Long endTime = activity.getActivityEndTime();
            statDate = Integer.parseInt(DateUtil.formatTimestamp(endTime, "yyyyMMdd")) - 1;
            stats = activityDao.findActivityIntegralStat(activityId, statDate);
        }

        List<Integer> uids = CollectionUtil.buildList(stats, Integer.class, "uid");
        Map<Integer, User> userMap = CollectionUtil.buildMap(userCoreDao.findUserById(uids, User.STATE_ACTIVATES, "name", "gender"), Integer.class, User.class, "id");

        Map<Integer, ActivitySignUp> activitySignUpMap = CollectionUtil.buildMap(activityDao.findActivitySignUps(activityId), Integer.class, ActivitySignUp.class, "userId");
        List<List<String>> exportData = new ArrayList<>();
        for (ActivityIntegralStat stat : stats) {
            User user = userMap.get(stat.getUid());

            List<String> row = new ArrayList<>();
            row.add(user.getId() + "-" + user.getName() +"-" + (user.getGender() == 1 ? "男" : "女"));
            row.add(activity.getId().toString() + "-" + activity.getThemeName());

            row.add(stat.getSort().toString());
            row.add(stat.getStatDate().toString());
            row.add(stat.getSumDistance().toString());
            row.add(stat.getSumStep().toString());
            row.add(stat.getSumIntegral().toString());

            row.add(stat.getDistance().toString());
            row.add(stat.getStep().toString());
            row.add(stat.getIntegral().toString());

            ActivitySignUp activitySignUp = activitySignUpMap.get(stat.getUid());
            if (activitySignUp == null){
                row.add(" - ");//联系人手机号码/联系人名称
                row.add("  ");//证件信息
            } else {
                if (StringUtils.isEmpty(activitySignUp.getMobileName())) {
                    row.add(activitySignUp.getMobilePhone() + "- ");
                } else {
                    row.add(activitySignUp.getMobilePhone() + "-" + activitySignUp.getMobileName());
                }
                if (CollectionUtils.isEmpty(activitySignUp.getActivitySignUpMembers())) {
                    row.add(" ");
                } else {
                    String member = "";
                    for (ActivitySignUpMember activitySignUpMember : activitySignUp.getActivitySignUpMembers()) {
                        member += activitySignUpMember.getIdCardType() + "-" + activitySignUpMember.getIdCard() + "\n";
                    }
                    row.add(member);
                }
            }
            exportData.add(row);
        }
        return exportData;
    }

    /**
     * 查询活动组信息
     *
     * @param activityId 活动编号
     */
    public List<ActivityGroup> findActivityGroup(Integer activityId) {
        Activity activity = activityCoreDao.findActivityById(activityId, "groups");
        if (CollectionUtils.isEmpty(activity.getGroups())) {
            return Collections.EMPTY_LIST;
        }
        return activity.getGroups();
    }

    /**
     * 设置赛事活动 报名模版
     *
     * @param activityId 活动编号
     * @param signUpMode 报名模版
     */
    public void modifySignUpMode(Integer activityId, Integer[] signUpMode) {
        Activity activity = activityCoreDao.findActivityById(activityId, "id", "type");
        if (Activity.TYPE_NORMAL == activity.getType()) {
            List<Integer> newSimUpMode = new ArrayList<>();
            List<Integer> all = new ArrayList<>(Activity.SIGN_UP_MODE_ALL.keySet());
            for (Integer a : all) {
                if (a == 15 || a == 16) {
                    continue;
                }
                newSimUpMode.add(a);
            }

            for (Integer s : signUpMode) {
                newSimUpMode.add(s);
            }

            signUpMode = new Integer[newSimUpMode.size()];
            for (Integer i = 0; i < newSimUpMode.size(); i++) {
                signUpMode[i] = newSimUpMode.get(i);
            }
        }
        activityCoreDao.updateActivityById(activityId, Update.update("signUpMode", signUpMode));
    }

    /**
     * 设置三方赛事的链接地址
     *
     * @param activityId 赛事编号
     * @param webLink 三方链接地址
     */
    public void setThreadLink(Integer activityId, String webLink) {
        activityCoreDao.updateActivityById(activityId, Update.update("focusTag", webLink));
    }

    /**
     * 设置积分赛事的区域标签
     *
     * @param activityId 赛事编号
     * @param cityTarget 城市标签
     */
    public void setActivityIntegralCity(Integer activityId, Integer cityTarget) {
        activityCoreDao.updateActivityById(activityId, Update.update("cityTarget", cityTarget));
    }

    /**
     * 设置最大组数
     *
     * @param activityId 赛事编号
     * @param maxGroupNum 最大数量
     */
    public void setActivityMaxGroupNum(Integer activityId, Integer maxGroupNum) {
        activityCoreDao.updateActivityById(activityId, Update.update("maxGroupNum", maxGroupNum));
    }

    /**
     * 增加赛事性别检查
     *
     * @param activityId 赛事编号
     * @param checkSex 性别
     */
    public void setActivityCheckSex(Integer activityId, String checkSex) {
        activityCoreDao.updateActivityById(activityId, Update.update("checkSex", checkSex));
    }

    /**
     * 设置三方赛事的链接地址
     *
     * @param activityId 赛事编号
     * @param focusTag 三方链接地址
     */
    public void setFocusTag(Integer activityId, String focusTag) {
        activityCoreDao.updateActivityById(activityId, Update.update("focusTag", focusTag));
    }

}
