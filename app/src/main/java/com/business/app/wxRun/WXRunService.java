package com.business.app.wxRun;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.SchedulerValue;
import com.business.core.entity.SysParam;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserWXStep;
import com.business.core.entity.wx.WXUser;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.schedulerValue.SchedulerValueCoreService;
import com.business.core.operations.sysManager.SysManagerService;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.operations.wxUser.WXUserCoreDao;
import com.business.core.sort.SortFactory;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.DateUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.RedisUtil;
import org.apache.commons.lang.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.*;

/**
 * Created by sin on 2015/10/13.
 */
@Service
public class WXRunService {

    private static Logger logger = LoggerFactory.getLogger(WXRunService.class);

    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;
    @Autowired
    private WXUserCoreDao wxUserCoreDao;
    @Autowired
    private DefaultDao defaultDao;
    @Autowired
    private SchedulerValueCoreService schedulerValueCoreService;
    @Autowired
    private SysManagerService sysManagerService;

    /**
     * 设置微信运动 步数
     * @param openid  用户唯一标示
     * @param step 步数
     * @return 0、成功  1、token 不存在 2、获取token 失败 3、用户不存在
     */
    public int setStep(Integer uid, String unionId, String openid, Integer step) {
        if (step == null || step == 0 || step > 80000) {
            return 1;
        }
        User user = userCoreDao.findUserById(uid, "lastSetWXStep", "addTime", "weight", "height", "age", "blackList");
        if (null == user) {
            return 3;
        }
        UserWXStep oldWxStep = user.getLastSetWXStep();

        if (oldWxStep != null) {
            if ((System.currentTimeMillis() - user.getLastSetWXStep().getRunTime()) < 1000 * SysParam.INSTANCE.WX_RUN_SET_STEP_TIME_MIX) {//500步，每秒4步，需要125秒才能跑出500步，而app是每500步进行一次步数设置
                return 2;
            }
        }

        UserWXStep wxStep = new UserWXStep();
        {//记录下最后一次设置微信步数
            if (StringUtils.isEmpty(unionId) || StringUtils.isEmpty(openid)) {
                if (oldWxStep == null || StringUtils.isEmpty(oldWxStep.getOpenid()) || StringUtils.isEmpty(oldWxStep.getUnionid())) {
                    return 3;//没有有效的微信帐号信息
                } else {
                    wxStep.setOpenid(oldWxStep.getOpenid());
                    wxStep.setUnionid(oldWxStep.getUnionid());
                }
            } else {
                wxStep.setOpenid(openid);
                wxStep.setUnionid(unionId);
            }
            wxStep.setStep(step);
            wxStep.setRunTime(System.currentTimeMillis());
            userCoreDao.updateUserById(uid, Update.update("lastSetWXStep", wxStep));
        }


        // 缓存 Redis
        WXUser wxUserCache = RedisUtil.cacheGet(RedisConstants.CACHE_WX_RUN_DB, wxStep.getUnionid(), WXUser.class);

        // 进行缓存
        if (wxUserCache == null) {
            wxUserCache = wxUserCoreDao.findWXUserByUnionId(wxStep.getUnionid());
            RedisUtil.cacheAddUpdate(RedisConstants.CACHE_WX_RUN_DB, wxStep.getUnionid(), wxUserCache, RedisConstants.CACHE_WX_RUN_EXPIRE_TIME);
        }

        // 用户不存在，或者没有关注公众号
        if (wxUserCache == null) {
            return 3;
        }

        String token = RedisConstants.getWeChatAccessToken();

        if (oldWxStep == null) { //首次进行微信步数设置
            return setWxStep(uid, 0, step, wxUserCache, token);
        } else {
            String today = DateUtil.formatTimestamp(System.currentTimeMillis(), "yyyyMMdd");
            if (!today.equals(DateUtil.formatTimestamp(oldWxStep.getRunTime(), "yyyyMMdd"))) {
                return setWxStep(uid, 0, step, wxUserCache, token);
            } else {
                return setWxStep(uid, oldWxStep.getStep(), step, wxUserCache, token);
            }
        }
    }

    /**
     * 设置微信步数,单次步数设置相差不能超过20000，后台这边对超过15000步的步数进行拆分设置
     * 微信不能 单次设置超过 2W ，这里为1.5W
     *
     * @param startStep 上次的步数
     * @param step 当前的步数
     * @param wxUserCache 微信用户信息
     * @param token 微信token
     */
    private Integer setWxStep(Integer uid, Integer startStep, Integer step, WXUser wxUserCache, String token) {
        Integer maxSetNumber = 15000;
        Integer stepNumber = (step - startStep) % maxSetNumber == 0 ? (step - startStep) / maxSetNumber : (step - startStep) / maxSetNumber + 1;

        try {
            if (step < maxSetNumber) { //理想情况
                handleWXStep(uid, wxUserCache, token, step);
                return 0;
            }

            //设置步数超过15000步
            for (int i = 1; i <= stepNumber; i++) {
                if (i == stepNumber) {
                    handleWXStep(uid, wxUserCache, token, step); //设置最大的步数
                } else {
                    handleWXStep(uid, wxUserCache, token, startStep + maxSetNumber * i); //设置过度的步数
                }
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("uid", uid);
            error.put("setStep", step);
            error.put("token", token);
            error.put("errorMessage", ExceptionUtils.getMessage(e));
            error.put("key", "微信步数设置异常");
            sysManagerService.saveCommonOversee("set-wx-step.in", JSON.toJSONString(error), "");
            return 3;//微信帐号信息过期
        }
        return 0;
    }

    /**
     * 进行微信步数设置api接口调用
     */
    private void handleWXStep(Integer uid ,WXUser wxUserCache, String token, Integer setStep) {
        String url = String.format("https://api.weixin.qq.com/hardware/bracelet/setstep?" +
                "access_token=%s&openid=%s&timestamp=%s&step=%s", token, wxUserCache.getOpenid(), System.currentTimeMillis() / 1000, String.valueOf(setStep));
        String responseText =  HttpUtil.get(url);
        logger.error("uid=" + uid + ",********" + responseText);
        try {
            Map<String, Object> rt = JSON.parseObject(responseText);
            if (rt.containsKey("errcode")) {
                int code = (int) rt.get("errcode");

                if (code > 0) {
                    /*Map<String, Object> error = new HashMap<>();
                    error.put("uid", uid);
                    error.put("setStep", setStep);
                    error.put("token", token);
                    error.put("wxCode", rt);
                    error.put("key", "微信步数设置异常");
                    sysManagerService.saveCommonOversee(JSON.toJSONString(error), "");*/
                }
            }
        } catch (Exception e) {
            Map<String, Object> error = new HashMap<>();
            error.put("uid", uid);
            error.put("setStep", setStep);
            error.put("token", token);
            error.put("responseText", responseText);
            error.put("errorMessage", ExceptionUtils.getMessage(e));
            error.put("key", "微信步数设置异常");
            sysManagerService.saveCommonOversee("set-wx-step.in", JSON.toJSONString(error), "");
        }
    }

    ///
    /// 活动特别版

    // TODO sin 整个 特别版可以去掉

    /**
     * 活动 版本 排行榜
     * 特定活动排版，根据版本号区分
     * @return  0、成功 1、不是活动版本，无法进行活动排名
     */
    public Object[] activityTeamRanking(String version) {
        System.out.println(SysParam.INSTANCE.ACTIVITY_USER_RUN_VERSION);
        List<JSONObject> jsonArraySrc = JSON.parseArray(SysParam.INSTANCE.ACTIVITY_USER_RUN_VERSION, JSONObject.class);
        List<JSONObject> jsonArray = new ArrayList<>(0);
        Collection<String> versions = new ArrayList<>();
        if (StringUtils.isEmpty(version)) {

            /// 书院的
            for (int i = 0; i <= 3; i++) {
                JSONObject jsonObject = jsonArraySrc.get(i);
                versions.add(jsonObject.get("version").toString());
                jsonArray.add(jsonObject);
            }
        }
        else {
            Integer lastNumber = Integer.valueOf(version.substring(version.lastIndexOf(".") + 1));
            // 三诺 和 企业公司的
            if (lastNumber >= 10 && lastNumber <= 16) {
                for (int i = 4; i <= 11; i++) {
                    JSONObject jsonObject = jsonArraySrc.get(i);
                    versions.add(jsonObject.get("version").toString());
                    jsonArray.add(jsonObject);
                }
            }
        }

        List<User> userList = userCoreDao.findUserByVersion(versions, "step", "id", "name", "avatar", "distance", "version");
        Map<String, List<User>> versionUserMultimap = CollectionUtil.buildMultimap(userList, String.class, User.class, "version");

        for (JSONObject jsonObject : jsonArray) {
            String jsonVersion = jsonObject.get("version").toString();
            List<User> versionUserList = versionUserMultimap.get(jsonVersion);
            /// 此版本数据是否有数据，没有则 置为 0 总距离
            if (!CollectionUtils.isEmpty(versionUserList)) {
                jsonObject.put("totalDistance", CollectionUtil.buildPropertyCount(versionUserList, Long.class, "distance"));
            }
            else {
                jsonObject.put("totalDistance", 0.0);
            }
        }

        /// 排序数据
        Collections.sort(jsonArray, SortFactory.WX_RUN_SORT);
        //版本全部累计模式
        return new Object[]{0, jsonArray};
    }

    /**
     * 活动 版本 排行榜详细
     * 团中成员排行
     * @return  0、成功 1、不是活动版本，无法进行活动排名
     */
    public Object[] activityTeamRankingDetail(String version) {
        JSONArray jsonArray = JSON.parseArray(SysParam.INSTANCE.ACTIVITY_USER_RUN_VERSION);
        Map<String, Object> versionObjectMap = CollectionUtil.buildMap(jsonArray, String.class, Object.class, "version");

        // 不是活动版本，无法进行活动排名
        if (!versionObjectMap.containsKey(version)) {
            return new Object[]{1};
        }
        List<User> userList = userCoreDao.findUserByVersion(version, "step", "id", "name", "avatar", "distance", "version");
        userCoreService.buildUserFileUrl(userList);
        //版本全部累计模式
        return new Object[]{0, userList};
    }

    /**
     * 获取数据库和Redis中的AccessToken
     */
    public SchedulerValue accessTokenInfo() {
        SchedulerValue schedulerValue = defaultDao.findOne(new Query(), SchedulerValue.class);

        schedulerValue.setRedisAccessToken(RedisConstants.getWeChatAccessToken());
        return schedulerValue;
    }

    /**
     * 更新Redis 缓存中的AccessToken
     */
    public void updateRedisAccessToken() {
        SchedulerValue schedulerValue = defaultDao.findOne(new Query(), SchedulerValue.class);

        RedisConstants.setWeChatAccessToken(schedulerValue.getWxMqAccessToken());
    }

    /**
     * 去微信获取最新的AccessToken，并保存到数据库
     */
    public void toWXGetNewAccessToken() {
        SchedulerValue oldSchedulerValue = defaultDao.findOne(new Query(), SchedulerValue.class);

        String newAccessToken = schedulerValueCoreService.getNewAccessToken();

        Update update = Update.update("wxMqAccessToken", newAccessToken).set("lastModifyTime", System.currentTimeMillis()).set("oldAccessToken", oldSchedulerValue.getWxMqAccessToken());
        defaultDao.upsert(new Query(), update, SchedulerValue.class);
    }
}
