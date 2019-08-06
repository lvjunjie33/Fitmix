package com.business.app.userSkipRopeShare;

import com.business.app.userSkipRope.UserSkipRopeDao;
import com.business.core.client.FileCenterClient;
import com.business.core.entity.user.User;
import com.business.core.entity.user.UserRun;
import com.business.core.entity.user.UserSkipRopeShare;
import com.business.core.operations.user.UserCoreDao;
import com.business.core.operations.user.UserCoreService;
import com.business.core.utils.HttpUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.MessageFormat;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Created by edward on 2016/5/24.
 */
@Service
public class UserSkipRopeShareService {

    @Autowired
    private UserSkipRopeDao userSkipRopeDao;
    @Autowired
    private UserCoreDao userCoreDao;
    @Autowired
    private UserCoreService userCoreService;

    ///
    ///   新 运动分享 (用户分享 确保运动记录已上传，不再保存 UserRunShare)


    /**
     * 用户跳绳 记录分享添加
     *
     * @param uid       用户编号
     * @param startTime 运动编号
     * @return 0、成功 1、数据不存在
     */
    public Object[] add(Integer uid, Long startTime) {
        UserRun userSkipRope = userSkipRopeDao.findByUidAndStartTime(uid, startTime, "startTime");
        if (userSkipRope == null) {
            return new Object[]{1};
        }

        return new Object[]{0, userSkipRope};
    }

    /**
     * 用户跳绳数据分享
     *
     * @param uid       用户编号
     * @param startTime 跳绳开始时间
     * @return 用户跳绳记录
     */
    public UserRun share(Integer uid, Long startTime) {
        UserRun userSkipRope = userSkipRopeDao.findByUidAndStartTime(uid, startTime);

        {
            userSkipRope = new UserRun();
            //TODO 测试代码
            userSkipRope = new UserRun();
            userSkipRope.setSkipDetail("aaa.htm");
            userSkipRope.setUid(uid);
            userSkipRope.setStartTime(System.currentTimeMillis());
            userSkipRope.setEndTime(System.currentTimeMillis() + 30 * 1000);
            userSkipRope.setCalorie(600L);
//            userSkipRope.setHeartRateAvg(300D);

            userSkipRope.setRunTime(1000L);
            userSkipRope.setSkipNum(500L);
            userSkipRope.setBpm(70);

            userSkipRope.setType(2);
            userSkipRope.setAddTime(System.currentTimeMillis());

            //默认值
            userSkipRope.setAddTime(System.currentTimeMillis());
            userSkipRope.setUpdateTime(System.currentTimeMillis());
            userSkipRope.setState(UserRun.STATE_EFFECTIVE);
            uid = 168310;
        }

        userSkipRope.setSkipDetail(FileCenterClient.buildUrl(userSkipRope.getSkipDetail()));
        User user = userCoreDao.findUserById(uid, User.BASIC_INFO_FIELDS);
        userCoreService.buildUserFileUrl(user);
        userSkipRope.setUser(user);

        if (true) {//TODO 测试代码
            userSkipRope.setSkipDetail(MessageFormat.format("{'array':[{'count':{0},'time':{1},{'count':{2},'time':{3}]}", 5, System.currentTimeMillis(), 15, System.currentTimeMillis() + 30 * 1000));
            return userSkipRope;
        }

        //轨迹文件或者记步文件空内容
        String nullTextStr = "{\"array\":[]}";
        //处理跳绳文件
        if (null != userSkipRope.getSkipDetail()) {
            String result = "";
            try {
                result = HttpUtil.get(userSkipRope.getSkipDetail());
                result = originalStr(result);
                if (!result.substring(result.length() - 2).equals("]}")) {
                    result += "]}";
                }
                userSkipRope.setSkipDetail(result);
            }catch (Exception e){
                System.out.println("跳绳文件不存在");
                userSkipRope.setSkipDetail(nullTextStr);
            }

        }else if(null == userSkipRope.getSkipDetail()){
            userSkipRope.setSkipDetail(nullTextStr);
        }
        return userSkipRope;
    }

    /***
     * 清除字符串中的空格、制表符、换行符、回车
     *
     * @param str
     * @return
     */
    private String originalStr(String str){
        Pattern p = Pattern.compile("//s*|\t|\r|\n");
        Matcher m = p.matcher(str);
        return  m.replaceAll("");
    }

    /**
     * 根据 用户编号和时间查询分享记录
     * @param uid   用户编号
     * @param startTime 开始时间
     * @return
     */
    public UserSkipRopeShare findUserSkipRopeShareByIdAndStartTime(Integer uid, Long startTime) {
        return  userSkipRopeDao.findUserSkipRopeByIdAndStartTime(uid, startTime);
    }

    /**
     * 保存 用户跳绳分享信息
     * @param userSkipRopeShare
     */
    public void saveUserSkipRopeShare(UserSkipRopeShare userSkipRopeShare) {
        userSkipRopeDao.saveUserSkipRopeShare(userSkipRopeShare);
    }
}
