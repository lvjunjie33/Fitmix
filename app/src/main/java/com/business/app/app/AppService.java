package com.business.app.app;

import com.business.core.client.FileCenterClient;
import com.business.core.constants.Constants;
import com.business.core.entity.logs.AppErrorLog;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.entity.logs.VerifyCodeLog;
import com.business.core.entity.sys.Announcement;
import com.business.core.entity.user.info.UserDevice;
import com.business.core.mongo.DefaultDao;
import com.business.core.operations.logs.AppErrorLogCoreDao;
import com.business.core.operations.logs.VerifyCodeCoreDao;
import com.business.core.operations.mail.MailCoreService;
import com.business.core.operations.sysManager.SysManagerDao;
import com.business.core.sms.SmsCoreService;
import com.business.core.utils.CollectionUtil;
import com.business.core.utils.HttpUtil;
import com.business.core.utils.MathUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * Created by Administrator on 2015-05-18.
 */
@Service
public class AppService {

    @Autowired
    private AppErrorLogCoreDao appErrorLogCoreDao;
    @Autowired
    private SysManagerDao sysManagerDao;
    @Autowired
    private MailCoreService mailCoreService;
    @Autowired
    private VerifyCodeCoreDao verifyCodeCoreDao;
    @Autowired
    private SmsCoreService smsCoreService;
    @Autowired
    private DefaultDao defaultDao;

    /**
     * 添加 app error 信息
     * @param content 错误信息
     * @param type log 类型
     * @param other 其他信息
     */
    @Deprecated
    public void appErrorUpload(String content, Integer type, Object other) {
        AppErrorLog iosErrorLog = new AppErrorLog();
        iosErrorLog.setContent(content);
        iosErrorLog.setObject(other);
        iosErrorLog.setType(type);
        iosErrorLog.setAddTime(System.currentTimeMillis());
        appErrorLogCoreDao.insertAppErrorLog(iosErrorLog);
    }

    /**
     * 添加 app error 信息
     * @param content 错误信息
     * @param other 其他信息
     * @param isIOS 安卓/iOS
     * @param other 其他信息
     */
    public void saveAppError(HttpServletRequest request, String content, boolean isIOS, String other) {
        SysErrorLog log = new SysErrorLog();
        log.setException(content);
        log.setUserId(null);
        if (isIOS) {
            log.setSys(Constants.CLIENT_APP_IOS);
        } else {
            log.setSys(Constants.CLIENT_APP_ANDROID);
        }

        log.setRequestParams(other);
        log.setUa(HttpUtil.getUA(request));
        log.setIp(HttpUtil.getIP(request));
        log.setAddTime(new Date());

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        sysManagerDao.saveSysErrorLog(log);
    }

    /**
     * 发送邮箱验证码
     *
     * @param email 邮箱帐号
     */
    public void getEmailVerificationCode(String email, String product) {
        VerifyCodeLog verifyCodeLog = new VerifyCodeLog();
        verifyCodeLog.setCode(MathUtil.random(123456, 987654) + "");
        verifyCodeLog.setKeywords(email);
        verifyCodeLog.setType(VerifyCodeLog.CODE_TYPE_MAIL);
        verifyCodeLog.setAddTime(System.currentTimeMillis());
        verifyCodeCoreDao.saveVerifyCode(verifyCodeLog);

        if (UserDevice.PRODUCT_ROC.equals(product)) {
            mailCoreService.sendMailVerificationCodeCL(email, verifyCodeLog.getCode(), verifyCodeLog.getAddTime());
        } else {
            mailCoreService.sendMailVerificationCode(email, verifyCodeLog.getCode(), verifyCodeLog.getAddTime());
        }
    }
    /**
     * 查询手机最新验证码
     */
    public VerifyCodeLog queryMobileVerificationCode(String mobile){
        VerifyCodeLog verifyCodeLog = verifyCodeCoreDao.findVerifyCodeByMobile(mobile);
        return verifyCodeLog;
    }

    /**
     * 发送手机验证码
     *
     * @param mobile 手机号码
     */
    public String getMobileVerificationCode(String mobile,String remoteAddr) {
        VerifyCodeLog verifyCodeLog;
        //通过ip地址获取验证码信息
        verifyCodeLog=verifyCodeCoreDao.findVerifyCodeByaddr(remoteAddr);
        if(verifyCodeLog==null) {
            verifyCodeLog=new VerifyCodeLog();
            sendVerifyCodeComm(verifyCodeLog,mobile,remoteAddr);
        }else {
            //防止历史数据报控指针
            if(verifyCodeLog.getOneAddTime()!=null) {
                //该ip第一次插入时间小于当前时间半小时以内
                if ((System.currentTimeMillis() - verifyCodeLog.getOneAddTime()) < (1 * 1000 * 60 * 30)) {
                    //该ip插入时间小于当前时间1秒以内
                    if ((System.currentTimeMillis() - verifyCodeLog.getAddTime()) < (1 * 1000)) {
                        //发送次数小于等于1次
                        if (verifyCodeLog.getSendCount() < 1) {
                            sendVerifyCodeComm(verifyCodeLog, mobile, remoteAddr);
                        }
                    } else {
                        //发送次数小于等于5次
                        if (verifyCodeLog.getSendCount() < 5) {
                            sendVerifyCodeComm(verifyCodeLog, mobile, remoteAddr);
                        }
                    }
                }
            }
            //防止历史数据报控指针
            if(verifyCodeLog.getOneAddTime()!=null) {
                //该ip超过半小时
                if ((System.currentTimeMillis() - verifyCodeLog.getOneAddTime()) >= (1 * 1000 * 60 * 30)) {
                    //清空发送次数
                    verifyCodeLog.setSendCount(0);
                    sendVerifyCodeComm(verifyCodeLog, mobile, remoteAddr);
                }
            }
        }
        return verifyCodeLog.getCode();
    }
    /**
     * 保存并发送短信公共方法
     * */
    public void sendVerifyCodeComm(VerifyCodeLog verifyCodeLog,String mobile,String remoteAddr){
        //第一次插入
        if(verifyCodeLog.getEffectiveTime()==null) {
            verifyCodeLog.setCode(MathUtil.random(123456, 987654) + "");
            verifyCodeLog.setEffectiveTime(System.currentTimeMillis());
        }else{
            //十分内发送同一个验证码
            if((System.currentTimeMillis()-verifyCodeLog.getEffectiveTime())<(1000*60*10)){
                verifyCodeLog.setCode(verifyCodeLog.getCode());
            }else{
                //超过十分钟发送随机验证码，同时记录
                verifyCodeLog.setCode(MathUtil.random(123456, 987654) + "");
                verifyCodeLog.setEffectiveTime(System.currentTimeMillis());
            }
        }
        verifyCodeLog.setKeywords(mobile);
        verifyCodeLog.setType(VerifyCodeLog.CODE_TYPE_MOBILE);
        verifyCodeLog.setAddTime(System.currentTimeMillis());
        verifyCodeLog.setRemoteAddr(remoteAddr);
        //短信发送次数等于空或等于0，设置值次数为1
        if (verifyCodeLog.getSendCount() == null || verifyCodeLog.getSendCount()==0) {
            verifyCodeLog.setSendCount(1);
            verifyCodeLog.setOneAddTime(System.currentTimeMillis());
        } else {
            verifyCodeLog.setSendCount(verifyCodeLog.getSendCount() + 1);
        }
        verifyCodeCoreDao.saveVerifyCode(verifyCodeLog);
        smsCoreService.sendVerificationCode(mobile, verifyCodeLog.getCode());
        }
    /**
     * 查询广告
     */
    public List<Announcement> findAnnouncement() {
//        List<Announcement> list = defaultDao.query(Query.query(Criteria.where("bTime").lt(System.currentTimeMillis()).and("eTime").gt(System.currentTimeMillis()).and("status").is(Constants.SWITCH_OPEN)), Announcement.class, Announcement.fields);
        List<Announcement> list = defaultDao.query(Query.query(Criteria.where("status").is(Constants.SWITCH_OPEN)), Announcement.class, Announcement.fields);
        if (StringUtils.isEmpty(list)) {
            list = Collections.EMPTY_LIST;
        } else {
            for (Announcement announcement : list) {
                announcement.setImgLink(FileCenterClient.buildUrl(announcement.getImgLink()));
            }
        }
        return list;
    }

}
