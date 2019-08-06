package com.business.app.activity;

import com.business.app.base.constants.CodeConstants;
import com.business.app.base.support.BaseServiceSupport;
import com.business.app.payOrder.PayOrderDao;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.Page;
import com.business.core.entity.activity.*;
import com.business.core.entity.payOrder.PayOrder;
import com.business.core.entity.stat.ActivityIntegralStat;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRealInfo;
import com.business.core.entity.user.UserRun;
import com.business.core.operations.activity.ActivityCoreDao;
import com.business.core.operations.activity.ActivityCoreService;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.userRun.UserRunCoreDao;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import java.text.DecimalFormat;
import java.util.*;

/**
 * Created by sin on 2015/9/10.
 * <p/>
 * 活动
 */
@Service
public class ActivityService extends BaseServiceSupport {

    @Autowired
    private ActivityCoreDao activityCoreDao;
    @Autowired
    private ActivityCoreService activityCoreService;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private ActivityDao activityDao;
    @Autowired
    private PayOrderDao payOrderDao;
    @Autowired
    private UserRunCoreDao userRunCoreDao;

    /**
     * 活动列表
     *
     * @param page 分页索引
     */
    public void list(Page<Activity> page, Integer uid) {
        page.getFilter().put("releaseStatus", Activity.RELEASE_STATUS_RELEASE);
        page.getFilter().put("status", Activity.STATUS_NORMAL);
        activityCoreDao.list(page);

        // 判断 用户是否注册过
        activityCoreService.buildFileUrls(page.getResult());

        /*List<ActivityUser> activityUserList =
                activityCoreDao.findActivityUserByIdsAndUid(
                        CollectionUtil.buildSet(page.getResult(), Integer.class, "id"),
                        uid, "id", "activityId", "tradeStatus");

        Map<Integer, ActivityUser> activityUserMap =
                CollectionUtil.buildMap(activityUserList, Integer.class, ActivityUser.class, "activityId");*/

        //新的用户是否报名信息

        List<ActivitySignUp> signUps = activityDao.findActivitySignUps(uid, "activityId");
        if (CollectionUtils.isEmpty(signUps)) {
            return;
        }

        Map<Integer, ActivitySignUp> activitySignUprMap =
                CollectionUtil.buildMap(signUps, Integer.class, ActivitySignUp.class, "activityId");

        for (Activity activity : page.getResult()) {
            // 设置相差 时间
            Integer startDiffHour = DateUtil.diffHour(DateUtil.parse(activity.getActivityBeginTime()), new Date(), true);
            Integer endDiffHour = DateUtil.diffHour(DateUtil.parse(activity.getActivityEndTime()), new Date(), true);
            activity.setStartDiffHour(startDiffHour);
            activity.setEndDiffHour(endDiffHour);
            // 是否已参加 活动标识
            if (activitySignUprMap.containsKey(activity.getId())) {
                activity.setRegister(true);
            }
            else {
                activity.setRegister(false);
            }
        }
    }

    /**
     * 获取一个 活动信息
     *
     * @param activityId 活动编号
     * @return 活动信息
     */
    public Activity getActivity(Integer activityId) {
        return activityCoreDao.findActivityById(activityId);
    }

    /**
     * 查询活动的 用户报名信息
     *
     * @param activityId 活动编号
     * @param uid 用户编号
     */
    public ActivitySignUp getActivitySignUp(Integer activityId, Integer uid) {
        ActivitySignUp activitySignUp = activityDao.findActivitySignUp(activityId, uid);
        //没有进行报名
        if (activitySignUp == null) {
            return null;
        }
        if ((activitySignUp.getTradeStatus() != null && PayOrder.PAY_STATE_SUCCESS == activitySignUp.getTradeStatus())|| activitySignUp.getNeedMoney() == 0) {
            activitySignUp.setTradeStatus(PayOrder.PAY_STATE_SUCCESS);
        } else {
            if (null != activitySignUp.getTradeNoPlatform()) {
                PayOrder payOrder = payOrderDao.findPayOrderByOrderNo(activitySignUp.getTradeNoPlatform(), "payState");
                if (payOrder == null) {
                    activitySignUp.setTradeStatus(PayOrder.PAY_STATE_CREATE);
                } else {
                    activitySignUp.setTradeStatus(payOrder.getPayState());
                }
            }
        }
        return activitySignUp;
    }

    /**
     * 查询活动的 用户报名信息
     *
     * @param activityId 活动编号
     * @param mobile 手机号码
     */
    public ActivitySignUp getActivitySignUp(Integer activityId, String mobile) {
        ActivitySignUp activitySignUp = activityDao.findActivitySignUp(activityId, mobile);
        //没有进行报名
        if (activitySignUp == null) {
            return null;
        }
        if (activitySignUp.getNeedMoney() == 0) {
            activitySignUp.setTradeStatus(PayOrder.PAY_STATE_SUCCESS);
            return activitySignUp;
        }
        if (null == activitySignUp.getTradeNoPlatform()) {
            return activitySignUp;
        }

        PayOrder payOrder = payOrderDao.findPayOrderByOrderNo(activitySignUp.getTradeNoPlatform(), "payState");
        if (null == payOrder) {
            activitySignUp.setTradeStatus(PayOrder.PAY_STATE_CREATE);
        } else {
            activitySignUp.setTradeStatus(payOrder.getPayState());
        }
        return activitySignUp;
    }

    /**
     * 活动报名
     *
     * @param activityId 活动编号
     * @param groupId 活动组编号
     * @param email 邮箱
     * @param mobileName 联系人名字
     * @param mobilePhone 联系人电话
     * @param emergencyName 紧急联系人名字
     * @param emergencyPhone 紧急联系人电话
     * @param signUpMembersInfo 报名人员信息
     */
    public int saveActivitySignUp(Integer userId, Integer activityId, String groupId, String email, String mobileName, String mobilePhone,
                                   String emergencyName, String emergencyPhone, List<Map<String, Object>> signUpMembersInfo,
                                  String userTeamName, String userTeamSlogan) {
        ActivitySignUp oldSignUp = activityDao.findActivitySignUp(activityId, mobilePhone, "id");
        if (oldSignUp != null) {
            return CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_MOBILE;
        }
        Activity activity = activityCoreDao.findActivityById(activityId);
        ActivityGroup currentGroup = null;
        for(ActivityGroup group : activity.getGroups()) {
            if (group.getId().equals(groupId)) {
                currentGroup = group;
                break;
            }
        }
        if (currentGroup == null) {
           return CodeConstants.ACTIVITY_SIGN_UP_ERROR;
        }
        Double needMoney = currentGroup.getNeedMoney();
        ActivitySignUp signUp = new ActivitySignUp();
        signUp.setActivityId(activityId);
        signUp.setEmail(email);
        signUp.setNeedMoney(needMoney);
        signUp.setMobileName(mobileName);
        signUp.setMobilePhone(mobilePhone);
        signUp.setEmergencyName(emergencyName);
        signUp.setEmergencyPhone(emergencyPhone);
        if (needMoney == 0) {//如果报名费用为零，则直接将支付状态改为支付成功
            signUp.setTradeStatus(PayOrder.PAY_STATE_SUCCESS);
        }
        if (!StringUtils.isEmpty(userId)) {
            signUp.setUserId(userId);
        }
        if (!StringUtils.isEmpty(userTeamName)) {
            signUp.setUserTeamName(userTeamName);
        }
        if (!StringUtils.isEmpty(userTeamSlogan)) {
            signUp.setUserTeamSlogan(userTeamSlogan);
        }

        signUp.setGroupId(currentGroup.getId());
        signUp.setGroupName(currentGroup.getGroupName());

        signUp.setActivitySignUpMembers(new ArrayList<ActivitySignUpMember>());
        signUp.setAddTime(System.currentTimeMillis());
        Set<String> idCards = new HashSet<>();
        Set<String> genders = new HashSet<>();
        Set<String> mobiles = new HashSet<>();

        for (Map<String, Object> groupMember : signUpMembersInfo) {
            ActivitySignUpMember member = new ActivitySignUpMember();
            if (groupMember.containsKey("name")) {
                member.setName(groupMember.get("name").toString());
            }
            if (groupMember.containsKey("memberMobile")) {
                member.setMobile(groupMember.get("memberMobile").toString());
                mobiles.add(member.getMobile());
            }
            if (groupMember.containsKey("gender")) {
                member.setGender(groupMember.get("gender").toString());
                genders.add(member.getGender());
            }
            if (groupMember.containsKey("idCard")) {
                member.setIdCard(groupMember.get("idCard").toString());
                idCards.add(member.getIdCard());
            }
            if (groupMember.containsKey("memo")) {
                member.setMemo(groupMember.get("memo") == null ? "" : groupMember.get("memo").toString());
            }
            if (groupMember.containsKey("clothesSize")) {
                member.setClothesSize(groupMember.get("clothesSize").toString());
            }
            if (groupMember.containsKey("idCardType")) {
                member.setIdCardType(groupMember.get("idCardType").toString());
            }
            if (groupMember.containsKey("groupMemberName")) {
                member.setGroupMemberName(groupMember.get("groupMemberName").toString());
            }
            if (groupMember.containsKey("bloodType")) {
                member.setBloodType(groupMember.get("bloodType").toString());
            }
            signUp.getActivitySignUpMembers().add(member);
        }

        //检查是否是标准赛事
        if (Activity.TYPE_NORMAL == activity.getType()) {
            if (idCards.size() < signUpMembersInfo.size()) {
                return CodeConstants.ACTIVITY_SIGN_UP_ERROR_DOUBLE_ID_CARD;
            }
            if (mobiles.size() < signUpMembersInfo.size() && signUpMembersInfo.size() > 1) {
                return CodeConstants.ACTIVITY_SIGN_UP_ERROR_DOUBLE_MOBILES;
            }
            long num = activityDao.findActivitySignUpByIdCardAndActivityId(idCards, activityId);
            if (num != 0) {
                return CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_ID_CARD;
            }

            if (!StringUtils.isEmpty(activity.getCheckSex())) {
                if(!genders.contains(activity.getCheckSex())) {// 没有这类性别
                    return CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_SEX;
                }
            }

            if (activity.getMaxGroupNum() != null && activityDao.findActivitySignUpByActivityId(activityId) + 1 > activity.getMaxGroupNum()) {
                return CodeConstants.ACTIVITY_SIGN_UP_ERROR_REPEAT_FULL;
            }
        }

        //活动报名信息入库
        activityDao.insertActivitySignUp(signUp);
        return CodeConstants.ACTIVITY_SIGN_UP_SUCCESS;
    }

    /**
     * 记录活动支付信息
     *
     * @param activityId 活动id
     * @param mobile 用户联系电话
     * @param tradePlatform 交易类型
     * @param orderNo 订单编号
     * @param tradeMoney 支付金额
     * @param tradeStatus 支付状态
     */
    public void saveActivityPayInfo(Integer activityId, String mobile, Integer tradePlatform, String orderNo, Double tradeMoney, Integer tradeStatus) {
        Update update = Update.update("tradePlatform", tradePlatform).set("tradeNoPlatform", orderNo)
                .set("tradeMoney", tradeMoney).set("tradeStatus", tradeStatus);
        update.set("tradeTime", System.currentTimeMillis());
        activityDao.modifyActivitySignUp(activityId, mobile, update);
    }

    /**
     * 修改活动报名信息的支付状态
     */
    @Deprecated
    public void modifyActivitySignUpPayStatus(Integer activityId, String mobile, Integer status) {
        Update update = Update.update("status", status);
        activityDao.modifyActivitySignUp(activityId, mobile, update);
    }

    /**
     * 活动 报名用户信息
     *
     * @param activityId  活动编号
     * @param uid         用户编号
     */
    @Deprecated
    public Object[] register(Integer activityId, Integer uid, Double activityMoney) {
        User user = userCoreDao.findUserById(uid, "id", "userRealInfo");
        if (null == user || null == user.getUserRealInfo()) {
            return new Object[]{1};
        }

        Activity activity = activityCoreDao.findActivityById(activityId, "id", "activityMoney");

        ActivityUser activityUser = new ActivityUser();
        activityUser.setUid(uid);
        activityUser.setActivityId(activityId);
        activityUser.setNeedMoney(activityMoney);
        activityUser.setTradeStatus(ActivityUser.TRADE_STATUS_WAIT_APY);
        activityUser.setAddTime(System.currentTimeMillis());
        activityUser.setUserRealInfo(user.getUserRealInfo());
        activityUser.setTradeMoney(activity.getActivityMoney());

        activityCoreDao.insertActivityUser(activityUser);

        return new Object[]{0, activityUser};
    }

    /**
     * 活动 报名用户信息
     *
     * @param activityId  活动编号
     * @param uid         用户编号
     */
    @Deprecated
    public Object[] registerJd(Integer activityId, Integer uid, Double activityMoney,
                               String name, Integer gender, String birthday, String idNumber, String nationality,
                               String liveCity, String address, String contantNumber, String emailAddress,
                               String emerqencyContact, String emerqencyContactNumber, String clothingSize, String orderNo) {
        User user = userCoreDao.findUserById(uid, "id", "userRealInfo");
        Activity activity = activityCoreDao.findActivityById(activityId, "id", "activityMoney");

        ActivityUser au = activityCoreDao.findActivityUserByActivityIdAndMobile(activity.getId(), contantNumber);
        if (au != null) {
            return new Object[]{1, au};
        }
        ActivityUser activityUser = new ActivityUser();
        activityUser.setActivityId(activityId);
        activityUser.setNeedMoney(activityMoney);
        activityUser.setTradeStatus(ActivityUser.TRADE_STATUS_WAIT_APY);
        activityUser.setAddTime(System.currentTimeMillis());
        activityUser.setTradeNo(orderNo);
        //activityUser.setUserRealInfo(user.getUserRealInfo());
        activityUser.setTradeMoney(activity.getActivityMoney());

        if (user != null && null != user.getUserRealInfo()) {
            activityUser.setUid(uid);
        }

        {
            UserRealInfo realInfo = new UserRealInfo();
            realInfo.setName(name);
            realInfo.setEmail(emailAddress);
            realInfo.setIdCard(idNumber);
            realInfo.setMobilePhone(contantNumber);
            realInfo.setGender(gender);
            realInfo.setAge(DateUtil.diffDay(DateUtil.parse(birthday, "yyyy-MM-dd"), new Date()) / 365);
            realInfo.setClothesSize(clothingSize);
            realInfo.setEmergencyPhone(emerqencyContactNumber);
            realInfo.setEmergencyName(emerqencyContact);
            realInfo.setCountry(nationality);
            realInfo.setCity(liveCity);
            realInfo.setDetail(address);
            activityUser.setUserRealInfo(realInfo);
        }

        activityCoreDao.insertActivityUser(activityUser);
        return new Object[]{0, activityUser};
    }

    /**
     * 发起交易前
     *
     * @param activityUserId 报名活动用户编号
     * @param tradeNo 商户交易订单好
     * @param tradeMoney 交易金额
     */
    @Deprecated
    public void tradeBeforeActivityUser(Long activityUserId, String tradeNo, Double tradeMoney) {
        activityCoreDao.updateActivityUserById(activityUserId,
                Update.update("tradeNo", tradeNo).
                        set("tradeMoney", tradeMoney).
                        set("tradeTime", System.currentTimeMillis()));
    }

    /***
     * 根据订单号修改订单状态
     * @param tradeNo 商户交易订单好
     * @param tradeNoPlatform 支付宝订单好
     * @param tradeStatus 支付状态
     */
    public void notifyActivityUser(String tradeNo, String tradeNoPlatform, int tradeStatus){
        activityCoreDao.updateActivityUserByTradeNo(tradeNo,
                Update.update("tradeStatus", tradeStatus).
                        set("tradeNoPlatform", tradeNoPlatform).
                        set("tradeSuccessTime", System.currentTimeMillis()).
                        inc("registerCount", 1));
    }

    /**
     * 用户活动报名信息
     *
     * @param activityId 活动编号
     * @param uid 用户id
     * @return
     */
    @Deprecated
    public ActivityUser getActivityUserByActivityIdAndUid(Integer activityId, Integer uid) {
        return activityCoreDao.findActivityUserByActivityIdAndUid(activityId, uid);
    }

    /**
     * 查询用户活动报名信息
     * @param activityUserId 用户活动报名信息
     * @return
     */
    @Deprecated
    public ActivityUser getActivityUserById(Integer activityUserId) {
        return activityCoreDao.findActivityUserById(activityUserId);
    }

    /**
     * 查询当前活动 积分排名信息
     * @param activityId 赛事活动编号
     * @param uid 用户编号
     */
    public Object[] findActivityStat(Integer activityId, Integer uid, Date currentDate, Activity activity) {

        if (activity.getActivityEndTime() <= currentDate.getTime() ) {
            List<ActivityIntegralStat> stats = activityCoreDao.findActivityIntegralStatLast(activityId, 0, 10);
            List<Integer> uids = CollectionUtil.buildList(stats, Integer.class, "uid");
            Map<Integer, User> userMap = CollectionUtil.buildMap(userCoreDao.findUserById(uids, User.STATE_ACTIVATES, "name", "avatar"), Integer.class, User.class, "id");
            for (ActivityIntegralStat stat : stats) {
                User user = userMap.get(stat.getUid());
                if (null != user.getAvatar() && !user.getAvatar().contains("http://")) {
                    user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
                }
                stat.setUser(user);
            }

            ActivityIntegralStat lastStat = activityDao.findActivityIntegralLast(activityId, uid);
            if (lastStat == null) {
                lastStat = new ActivityIntegralStat();
                lastStat.setActivityId(activityId);
                lastStat.setUid(uid);
            }
            lastStat.setIntegral(0L);
            lastStat.setStep(0L);
            lastStat.setDistance(0L);
            return new Object[]{1, lastStat, stats};
        }

        ActivitySignUp signUp = activityDao.findActivitySignUp(activityId, uid, "mobilePhone", "activityId");
        if (signUp == null) {
            return new Object[]{0};
        }

        List<ActivityIntegralStat> stats = activityCoreDao.findActivityIntegralStat(Integer.parseInt(DateUtil.format(DateUtil.getDayBefore(currentDate), "yyyyMMdd")), activityId, 0, 10);
        List<Integer> uids = CollectionUtil.buildList(stats, Integer.class, "uid");
        Map<Integer, User> userMap = CollectionUtil.buildMap(userCoreDao.findUserById(uids, User.STATE_ACTIVATES, "name", "avatar"), Integer.class, User.class, "id");
        for (ActivityIntegralStat stat : stats) {
            User user = userMap.get(stat.getUid());
            if (user.getAvatar() != null && !user.getAvatar().contains("http://")) {
                user.setAvatar(FileCenterClient.buildUrl(user.getAvatar()));
            }
            stat.setUser(user);
        }

        Long begin = DateUtil.getDayBegin(currentDate).getTime();
        Long end = DateUtil.getDayEnd(currentDate).getTime();

        List<Integer> types = new ArrayList<>();
        types.add(UserRun.TYPE_UNKNOWN);
        types.add(UserRun.TYPE_RUN);

        //用户运动记录
        List<UserRun> userRuns = userRunCoreDao.findUserRunByTime(uid, begin, end, types, "step", "distance", "uid");

        ActivityIntegralStat stat = new ActivityIntegralStat();
        stat.setSumDistance(0L);
        stat.setSumIntegral(0L);
        stat.setSumStep(0L);

        stat.setDistance(0L);
        stat.setStep(0L);
        stat.setIntegral(0L);

        stat.setAddTime(currentDate.getTime());
        stat.setStatDate(DateUtil.formatDate2Int(currentDate));

        stat.setUid(uid);
        stat.setActivityId(signUp.getActivityId());

        DecimalFormat decimalFormat = new java.text.DecimalFormat("#");
        for (UserRun run : userRuns) {
            stat.setStep(stat.getStep() + run.getStep());
            stat.setDistance(stat.getDistance() + run.getDistance());
            Long setIntegral = Long.parseLong(decimalFormat.format(stat.getStep() * 1.0 / 5000));
            Long distanceIntegral = Long.parseLong(decimalFormat.format(stat.getDistance() * 1.0 / 3000));
            if (setIntegral > distanceIntegral) {
                stat.setIntegral(setIntegral);
            } else {
                stat.setIntegral(distanceIntegral);
            }
        }

        ActivityIntegralStat oldStat = activityDao.findActivityIntegralStatByUidAndActivityId(signUp.getActivityId(), uid,
                Integer.parseInt(DateUtil.format(DateUtil.getDayBefore(currentDate), "yyyyMMdd")));

        if (oldStat != null) {
            stat.setSumDistance(oldStat.getSumDistance() + stat.getDistance());
            stat.setSumStep(oldStat.getSumStep() + stat.getStep());
            stat.setSumIntegral(oldStat.getSumIntegral() + stat.getIntegral());
            stat.setSort(oldStat.getSort());
        } else {
            stat.setSumDistance(stat.getDistance());
            stat.setSumStep(stat.getStep());
            stat.setSumIntegral(stat.getIntegral());
        }

        return new Object[]{1, stat, stats};
    }

    /**
     * 增加访问量
     * @param activityId 赛事编号
     */
    public void incViewCount(Integer activityId) {
        Update update = new Update();
        update.inc("viewCount", 1);
        activityCoreDao.updateActivityById(activityId, update);
    }
}
