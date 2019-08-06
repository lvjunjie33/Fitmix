package com.business.core.redis;

import com.business.core.constants.MsgConstants;
import com.business.core.entity.club.ClubMember;
import com.business.core.entity.club.ClubMessage;
import com.business.core.entity.club.ClubNotice;
import com.business.core.entity.community.discuss.Discuss;
import com.business.core.entity.community.discuss.Theme;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.DefaultDao;
import com.business.core.utils.BeanManager;
import com.business.core.utils.RedisUtil;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import redis.clients.jedis.Jedis;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by edward on 2017/9/18.
 */
public class RedisMsgManager {

    private static void send(String channel, String msgId) {
        Jedis jedis = RedisUtil.getResourcePubSub();
        jedis.publish(channel, msgId);
        //释放redis
        RedisUtil.returnResource(jedis);
    }

    /**
     * 发送俱乐部消息
     *
     * @param clubMessage 俱乐部消息
     */
    public static void sendClubMsg(ClubMessage clubMessage) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);

        List<ClubMember> clubMembers = defaultDao.query(Query.query(Criteria.where("clubId").is(clubMessage.getClubId())), ClubMember.class, "uid");
        Map<String, String> map = new HashMap<>();
        map.put("clubMsgId", clubMessage.getId().toString());
        for (ClubMember clubMember : clubMembers) {
            if (clubMember.getUid().equals(clubMessage.getUid())) {
                continue;
            }
            map.put("uid", clubMember.getUid().toString());

            Message message = new Message();
            message.setAddTime(System.currentTimeMillis());
            message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
            message.setSelectChannel(MsgConstants.CHANNEL_CLUB_MSG_SEND);
            message.setMsgBody(map);
            BeanManager.getBean(DefaultDao.class).save(message);
            send(message.getSelectChannel(), message.getId().toString());
        }

    }

    /**
     * 发送俱乐部通知
     *
     * @param clubNotice 俱乐部通知
     */
    public static void sendClubNotice(ClubNotice clubNotice) {
        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);

        List<ClubMember> clubMembers = defaultDao.query(Query.query(Criteria.where("clubId").is(clubNotice.getClubId())), ClubMember.class, "uid");

        Map<String, String> map = new HashMap<>();
        map.put("clubNoticeId", clubNotice.getId().toString());
        for (ClubMember clubMember : clubMembers) {
            if (clubMember.getUid().equals(clubNotice.getUid())) {
                continue;
            }

            map.put("uid", clubMember.getUid().toString());

            Message message = new Message();
            message.setAddTime(System.currentTimeMillis());
            message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
            message.setSelectChannel(MsgConstants.CHANNEL_CLUB_NOTICE_SEND);
            message.setMsgBody(map);
            BeanManager.getBean(DefaultDao.class).save(message);
            send(message.getSelectChannel(), message.getId().toString());
        }
    }

    /**
     * 用户运动完成 统计
     *
     * @param userRun 用户运动数据
     */
    public static void sendUserRunTask(UserRun userRun) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_TASK);
        message.setMsgBody(userRun.getId());
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }
    /**
     * 用户登录获取IP
     *
     * @param user 用户信息
     * @param ip ip地址
     */
    public static void sendUserLoginTask(String ip, User user) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_USER_LOGIN_TASK);
        message.setMsgBody(user.getId());
//        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(),user.getId().toString()+","+ip);
    }


    /**
     * 俱乐部用户排名 统计
     *
     * @param userRun 用户运动数据
     */
    public static void sendClubUserRunRankTask(UserRun userRun) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_CLUB_USER_RUN_RANK_TASK);
        message.setMsgBody(userRun.getId());
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 更新用户运动排行榜
     *
     * @param userRun 用户运动数据
     */
    public static void sendRunRankTaskHandleMsg(UserRun userRun) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_RANK_TASK);
        message.setMsgBody(userRun.getId());
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 话题推荐
     *
     * @param themeId 话题
     */
    public static void sendThemeRecommend(Integer targetUid, String themeId, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_THEME_RECOMMEND_SEND);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        map.put("themeId", themeId);
        if (targetUid != null) {
            map.put("targetUid", targetUid.toString());
        }
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 赛事推荐
     */
    public static void sendActivityRecommend(Integer targetUid, String activityId, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_ACTIVITY_SEND);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        map.put("activityId", activityId);
        if (targetUid != null) {
            map.put("targetUid", targetUid.toString());
        }
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 赛事推荐
     */
    public static void sendMixRecommend(Integer targetUid, String mixId, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_TYPE_MIX_SEND);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        map.put("mixId", mixId);
        if (targetUid != null) {
            map.put("targetUid", targetUid.toString());
        }
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    public static void sendRadioRecommend(Integer targetUid, String radioId, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_TYPE_RADIO);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        map.put("radioId", radioId);
        if (targetUid != null) {
            map.put("targetUid", targetUid.toString());
        }
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 视频推送
     *
     * @param targetUid 用户编号
     * @param videoId 视频编号
     */
    public static void sendVideoRecommend(Integer targetUid, String videoId, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_TYPE_VIDEO);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        map.put("videoId", videoId);
        if (targetUid != null) {
            map.put("targetUid", targetUid.toString());
        }
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 发送训练计划通知命令
     */
    public static void sendRunPlanNotice(Integer targetUid, String content, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_PLAN_SEND);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        map.put("targetUid", targetUid.toString());
        map.put("content", content);
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 发送第三方链接活动通知
     */
    public static void sendOtherLinkNotice(Integer targetUid, String title, String content, String link, Integer adminId) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_TYPE_OTHER_LINK);
        message.setAdminId(adminId);

        Map<String, String> map = new HashMap<>();
        if (targetUid != null) {
            map.put("targetUid", targetUid.toString());
        }
        map.put("title", title);
        map.put("link", link);
        map.put("content", content);
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 发送私密消息
     *
     * @param groupId 用户组编号
     * @param content 内容
     */
    public static Message sendUserPrivateMsg(Long groupId, Integer fromUid, String content) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_TYPE_USER_PRIVATE_MSG);

        Map<String, String> map = new HashMap<>();
        map.put("groupId", groupId.toString());
        map.put("content", content);
        map.put("fromUid", fromUid.toString());
        message.setMsgBody(map);

        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
        return message;
    }

    /**
     * 用户运动数据汇总(周、月、年)统计
     *
     * @param userRun 用户运动数据
     */
    public static void sendUserRunStatTaskMsg(UserRun userRun) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_STAT_TASK);
        message.setMsgBody(userRun.getId());
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 话题  推送回答
     *
     * @param answer 回答
     */
    public static void sendThemeAnswer(Theme answer, Integer targetUid, String content) {

        Map<String, String> body = new HashMap<>();
        body.put("targetUid", targetUid.toString());
        body.put("fromUid", answer.getUid().toString());
        body.put("answerId", answer.getId().toString());
        body.put("themeId", answer.getParentThemeId().toString());
        body.put("content", content);

        Message message = new Message();
        message.setAddTime(answer.getAddTime());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_THEME_ANSWER);
        message.setMsgBody(body);
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 话题   评论
     * @param discuss 评论
     */
    public static void sendAnswerDiscuss(Discuss discuss, Integer targetUid, String content) {
        Map<String, String> body = new HashMap<>();
        body.put("targetUid", targetUid.toString());
        body.put("fromUid", discuss.getUid().toString());
        body.put("discussId", discuss.getId().toString());
        body.put("answerId", discuss.getThemeId().toString());
        body.put("content", content);

        Message message = new Message();
        message.setAddTime(discuss.getAddTime());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_ANSWER_DISCUSS);
        message.setMsgBody(body);
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }

    /**
     * 上传手表运动数据解压文件 统计
     *
     * @param userRun 用户运动数据
     */
    public static void sendUserRunWatchTask(UserRun userRun) {
        Message message = new Message();
        message.setAddTime(System.currentTimeMillis());
        message.setStatus(MsgConstants.HANDLE_STATUS_FALSE);
        message.setSelectChannel(MsgConstants.CHANNEL_USER_RUN_WATCH_TASK);
        message.setMsgBody(userRun.getId());
        BeanManager.getBean(DefaultDao.class).save(message);
        send(message.getSelectChannel(), message.getId().toString());
    }
}
