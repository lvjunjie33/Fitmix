package com.business.app.notice;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.business.core.constants.ApplicationConfig;
import com.business.core.constants.RedisConstants;
import com.business.core.entity.notice.Notice;
import com.business.core.entity.notice.log.NoticeLog;
import com.business.core.entity.user.User;
import com.business.core.operations.noticeLog.NoticeLogCoreDao;
import com.business.core.operations.noticeLog.NoticeLogCoreService;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.RedisUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by sin on 2015/12/9.
 */
@Service
public class NoticeService {

    @Autowired
    private NoticeDao noticeDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private NoticeLogCoreDao noticeLogCoreDao;
    @Autowired
    private NoticeLogCoreService noticeLogCoreService;

    ///
    /// 通知

    /**
     *
     * 通知：对账
     *
     *  1、我们通知别人 2、另一种别人来请求我们
     *
     * 助手：我们通知对方
     *
     * 点入：对方请求我们
     *
     * @param uid 用户编号
     * @param idfa idfa
     */
    public void notice(Integer uid, String idfa) {
        String noticeJson = RedisUtil.cacheGet(RedisConstants.CACHE_NOTICE_NEW_DB, idfa);
        if (!StringUtils.isEmpty(noticeJson)) {
            Notice notice = JSON.parseObject(noticeJson, Notice.class);
            // 位置 notice 跳过
            if (notice.getChannel().equals(Notice.CHANNEL_UNKNOWN)) {
                return;
            }
            // 通知 爱思
            if (notice.getChannel().equals(Notice.CHANNEL_AI_SI)) {
                User user = userCoreDao.findUserById(uid, User.BASIC_INFO_FIELDS);
                aiSiNotice(notice, user);
            }
        }
        else {
            // 没有直接加入或者更新 缓存
            RedisUtil.cacheAddUpdate(RedisConstants.CACHE_NOTICE_NEW_DB, idfa,
                    buildNotice(idfa, Notice.CHANNEL_UNKNOWN), RedisConstants.CACHE_NOTICE_EXPIRE_TIME);
        }
    }

    /**
     * 添加 idfa 信息
     *
     * 1、爱思通知我们用户来了
     *
     * @param idfa idfa
     * @param channel 渠道
     * @return 0、成功 1、重复添加
     */
    public int addNotice(String idfa, String channel) {
        String noticeJson = RedisUtil.cacheGet(RedisConstants.CACHE_NOTICE_NEW_DB, idfa);
        if (StringUtils.isEmpty(noticeJson)) {
            Notice notice = noticeDao.findNoticeByIdfa(idfa, null, null);
            if (notice == null) {
                RedisUtil.cacheAddUpdate(RedisConstants.CACHE_NOTICE_NEW_DB, idfa,
                        buildNotice(idfa, channel), RedisConstants.CACHE_NOTICE_EXPIRE_TIME);
            }
            else {
                return 1;
            }
        }
        return 0;
    }

    ///
    /// 爱思

    public void aiSiNotice(Notice notice, User user) {
        try {
            // 通知 爱思
            String url = "http://ios.api.i4.cn/appactivatecb.xhtml?aisicid=200147&appid=" + ApplicationConfig.IOS_APP_ID + "&aisi=253612&idfa=" + notice.getIdfa() + "&rt=1";
            String result = HttpUtil.get(url);
            JSONObject jsonObject = JSONObject.parseObject(result);
            // 是否成功
            handlerResult(jsonObject.get("success").equals("true"), Notice.CHANNEL_AI_SI, result, notice, user);
        } catch (Exception e) {
            noticeLogCoreService.buildNoticeLog(user.getId(), user.getName(), notice.getIdfa(), String.format("notice aiSi error result: %s", e));
        }
    }

    ///
    /// 点入

    public Object[] dianRu(List<String> idfaList) {
        if (CollectionUtils.isEmpty(idfaList)) {
            return new Object[]{1};
        }
        return new Object[]{0, verifyIdfa(idfaList, Notice.CHANNEL_PING_GUO_DIAN_RU)};
    }

    ///
    /// redis 对账

    public Map<String, String> verifyIdfa(List<String> idfaList, String channel) {
        Map<String, String> resultParamMap = new LinkedHashMap<>();
        for (String idfa : idfaList) {
            String noticeJson = RedisUtil.cacheGet(RedisConstants.CACHE_NOTICE_NEW_DB, idfa);
            if (!StringUtils.isEmpty(noticeJson)) {
                JSONObject jsonObject = JSON.parseObject(noticeJson);
                jsonObject.put("channel", channel);
                jsonObject.put("status", Notice.STATUS_SUCCESS);
                RedisUtil.cacheAddUpdate(RedisConstants.CACHE_NOTICE_NEW_DB, idfa, jsonObject, RedisConstants.CACHE_NOTICE_EXPIRE_TIME);

                resultParamMap.put(idfa, "1");
            }
            else {
                resultParamMap.put(idfa, "0");
            }
        }
        return resultParamMap;
    }

    /**
     * 对账 日志
     *
     * 爱思
     *
     * @param isSuccess 对账是否成功
     * @param channel 渠道
     * @param result 请求idfa
     * @param notice 通知对象
     * @param user 用户
     */
    private void handlerResult(boolean isSuccess, String channel, String result, Notice notice, User user) {
        // 是否成功
        if (isSuccess) {
            Notice noticeNew = noticeDao.findAndModifyNew(notice.getId(), Update.update("status", Notice.STATUS_SUCCESS));
            NoticeLog noticeLog =  noticeLogCoreService.
                    buildNoticeLog(user.getId(), user.getName(), notice.getIdfa(),
                            String.format("notice %s success [idfa=%s] [noticeId=%s] [uid=%s]", channel, notice.getIdfa(), noticeNew.getId(), user.getId()));
            noticeLogCoreDao.insertNoticeLog(noticeLog);
        }
        else {
            NoticeLog noticeLog =  noticeLogCoreService.
                    buildNoticeLog(user.getId(), user.getName(), notice.getIdfa(),
                            String.format("notice %s fail [noticeId=%s] ,result:%s",  channel, notice.getId(), result));
            noticeLogCoreDao.insertNoticeLog(noticeLog);
        }
    }

    /**
     * 构建 一个 idfa
     *
     * @param idfa idfa
     * @param channel 渠道
     */
    public Notice buildNotice(String idfa, String channel) {
        Notice notice = new Notice();
        notice.setIdfa(idfa);
        notice.setChannel(channel);
        notice.setStatus(Notice.STATUS_WAIT);
        notice.setAddTime(System.currentTimeMillis());
        return notice;
    }
}
