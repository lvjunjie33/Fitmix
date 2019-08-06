package com.business.msg.server.task;

import com.business.core.client.AliyunCenterClient;
import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.constants.FileConstants;
import com.business.core.constants.MsgConstants;
import com.business.core.entity.msg.Message;
import com.business.core.entity.user.UserEveryDayRun;
import com.business.core.entity.user.UserRun;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.utils.BeanManager;
import com.business.msg.MsgSubscribeAnnotation;
import com.business.msg.core.RedisConcurrentlyCommand;
import com.business.msg.util.zip.DownLoadFile;
import com.business.msg.util.zip.ZipDecompressing;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.util.StringUtils;

import java.io.File;
import java.util.List;

/**
 * Created by edward on 2017/10/30.
 */
@MsgSubscribeAnnotation(channel = MsgConstants.CHANNEL_USER_RUN_TASK)
public class UserRunTask implements RedisConcurrentlyCommand {

    private static Logger logger = LoggerFactory.getLogger(UserRunTask.class);

    @Override
    public void execute(String msgId) {

        DefaultDao defaultDao = BeanManager.getBean(DefaultDao.class);
        Message message = defaultDao.findOne(Query.query(Criteria.where("id").is(Long.parseLong(msgId))), Message.class);

        Long userRunId = Long.parseLong(message.getMsgBody().toString());
        UserRun userRun = defaultDao.findOne(Query.query(Criteria.where("id").is(userRunId)), UserRun.class);

        handleZipFile(userRun);

        {
            if (UserRun.TYPE_SKIP_ROPE.equals(userRun.getType())) { //跳绳进行心率总数的统计
//                statHeartRate(userRun, defaultDao);
                //更新用户最后跳绳记录
                updateSkipRope(userRun);
                return;
            } else {//跑步进行的心率总数统计
//                statHeartRate(userRun, defaultDao);
            }
        }

        // EveryDay 数据
        add(userRun.getId(), userRun.getUid(), userRun.getRunTime(), userRun.getStep(), userRun.getCalorie(), userRun.getDistance(), 0L);

        logger.error("___________handle_____" + userRun.getUid() + "________ok________________________");
    }

    private void handleZipFile(UserRun userRun) {
        if (!StringUtils.isEmpty(userRun.getDetail()) && !StringUtils.isEmpty(userRun.getSkipDetail())) {
            return;
        }

        if (StringUtils.isEmpty(userRun.getZipUrl())) {
            return;
        }

        try {
            String name = userRun.getId().toString();
            String path = System.getProperty("user.dir") + "/";
            String zipUrl = FileCenterClient.buildUrl(userRun.getZipUrl());
            //下载
            DownLoadFile.handle(zipUrl, name + ".zip", path);
            //解压
            List<String> files = ZipDecompressing.handle(path + name + ".zip", path);

            String stepDetailFile = null;
            String detailFile = null;
            if (Constants.TERMINAL_ANDROID == userRun.getDeviceType()) {//安卓压缩包处理
                //文件上传阿里云文件服务器
                for (String file : files) {
                    if (file.contains("step")) {
                        userRun.setStepDetail(AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_STEP_DETAIL.toString(), file, DownLoadFile.getContent(file)));
                    }

                    if (file.contains("json")) {
                        userRun.setDetail(AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_RUN_DETAIL.toString(), file, DownLoadFile.getContent(file)));
                    }
                    //销毁解压文件
                    new File(file).delete();
                }
            } else if (Constants.TERMINAL_IOS == userRun.getDeviceType()) { //iOS压缩包处理
                //文件上传阿里云文件服务器
                for (String file : files) {
                    if (file.contains(".json")) {
                        if (file.contains("kSaveSportKey")) {
                            userRun.setStepDetail(AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_STEP_DETAIL.toString(), file, DownLoadFile.getContent(file)));
                        } else {
                            userRun.setDetail(AliyunCenterClient.putFile(FileConstants.FILE_TYPE_USER_RUN_DETAIL.toString(), file, DownLoadFile.getContent(file)));
                        }
                    }
                    //销毁解压文件
                    new File(file).delete();
                }
            }

            //销毁压缩包
            new File(path + name + ".zip").delete();
            //销毁压缩解压目录
            new File(path + name);

            //清理本地文件

            Update update = new Update();
            if (!StringUtils.isEmpty(userRun.getDetail())) {
                update.set("detail", userRun.getDetail());
            }
            if (!StringUtils.isEmpty(userRun.getStepDetail())) {
                update.set("stepDetail", userRun.getStepDetail());
            }
            BeanManager.getBean(DefaultDao.class).modifyFirst(Query.query(Criteria.where("id").is(userRun.getId())), update, UserRun.class);

        }catch (Exception e) {
            logger.error(ExceptionUtils.getMessage(e));
        }
    }

    private void updateSkipRope(UserRun sumSkipRope) {
        BeanManager.getBean(UserCoreDao.class).updateUserById(sumSkipRope.getUid(),
                new Update().inc("sumSkipRope.runTime", sumSkipRope.getRunTime()).inc("sumSkipRope.skipNum", sumSkipRope.getSkipNum())
                        .inc("sumSkipRope.calorie", sumSkipRope.getCalorie()));
    }

    /**
     * 添加 用户每天运动
     * @param runId 用户运动
     * @param uid 用户编号
     * @param runTime 运动时间
     * @param step 步数
     * @param calorie 卡路里
     * @param distance 距离
     * @param rememberStep 计步器 步数
     */
    private void add(Long runId, Integer uid, Long runTime, Long step, Long calorie, Long distance, Long rememberStep) {
        UserEveryDayRun userEveryDayRun = new UserEveryDayRun();
        userEveryDayRun.setRunId(ImmutableSet.of(runId));
        userEveryDayRun.setUid(uid);
        userEveryDayRun.setRunTime(runTime);
        userEveryDayRun.setStep(step);
        userEveryDayRun.setCalorie(calorie);
        userEveryDayRun.setDistance(distance);

        userEveryDayRun.setCalorie(rememberStep);
        userEveryDayRun.setAddTime(System.currentTimeMillis());

        BeanManager.getBean(DefaultDao.class).save(userEveryDayRun);
    }
}
