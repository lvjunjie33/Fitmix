package com.business.core.operations.sysManager;

import com.business.core.constants.Constants;
import com.business.core.entity.Page;
import com.business.core.entity.logs.SysErrorLog;
import com.business.core.utils.DateUtil;
import com.business.core.utils.HttpUtil;
import com.google.common.collect.ImmutableMap;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Date;

/**
 * Created by edward on 2016/4/18.
 * 系统管理 Service
 */
@Service
public class SysManagerService {

    private static final ImmutableMap<String, String> appUrls = ImmutableMap.<String, String>builder()
            .put("/ini", "初始化接口报错了")
            .put("/user/login", "登录接口报错了")
            .put("/user/register", "注册接口报错了")
            .put("/user/modify-info", "用户信息修改报错了")
            .put("/user/email-recovery-password", "邮件找回密码报错了")
            .put("/user/modify-password", "修改密码报错了")
            .put("/user/modify-real-info", "修改真实信息报错了")
            .put("/user/user-verify-code", "用户手机注册验证码发送报错")
            .put("/user/mobile-register", "用户手机注册报错了")
            .put("/user/replace-mobile", "更换手机号码报错了")
            .put("/user/mobile-reset-password", "手机号码更换密码报错了")
            .put("/user/binding", "绑定 用户账号报错了")
            .put("/user/unbinding", "解除绑定 用户账号报错了")
            .put("/activity/list", "赛事列表报错了")
            .put("/activity/get-activity-list", "新版赛事列表报错了")
            .put("/ali-pay/h5Pay", "阿里H5支付报错了")
            .put("/ali-pay/return-url", "阿里支付GET返回信息报错了")
            .put("/ali-pay/notify-url", "阿里支付POST返回信息报错了")
            .put("/we_chat/pay/public_number_pay", "微信公众号支付报错了")
            .put("/we_chat/pay/public_number_auth_redirect", "微信公众平台 授权认证报错了")
            .put("/we_chat/pay/pay_notify", "微信支付 通知报错了")
            //TODO 其他需要监听的接口
            .build();

    private static final ImmutableSet<String> mails = ImmutableSet.<String>builder()
            .add("zhangss@ifitmix.com")
            .add("fansl@ifitmix.com")
            /*.add("zhangt@ifitmix.com")
            .add("qiaoq@ifitmix.com")
            .add("sunj@ifitmix.com")
            .add("cheny@ifitmix.com")
            .add("meixf@ifitmix.com")
            .add("guosy@ifitmix.com")*/
            //TODO 增加需要异常监控信息的 用户邮箱列表
            .build();

    @Autowired
    private SysManagerDao sysManagerDao;

    /**
     * 持久化异常到{@link SysErrorLog}里
     *
     * @param request  请求
     * @param response 响应
     * @param ex       异常
     */
    public void saveInterfaceExceptionLog(HttpServletRequest request, HttpServletResponse response, Exception ex, int sys) {

        SysErrorLog log = new SysErrorLog();
        log.setException(ExceptionUtils.getStackTrace(ex).substring(0, 500));
        log.setMatchedPath(request.getRequestURI());

        log.setUserId(null);

        log.setSys(sys);
        log.setRequestUri(request.getRequestURI());
        log.setRequestMethod(request.getMethod());
        log.setRequestParams(HttpUtil.buildQueryString(request));
        log.setUa(HttpUtil.getUA(request));
        log.setIp(HttpUtil.getIP(request));
        log.setAddTime(new Date());

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        sysManagerDao.saveSysErrorLog(log);

        if (Constants.SERVER_NAME_APP.equals(sys)) {// app重要的接口异常短信推送/邮件推送 TODO 是否需要短信提醒待定
            String key = log.getRequestUri().substring(0, log.getRequestUri().indexOf("."));
            if (appUrls.containsKey(key)) {
                String content = "接口:" + key + "&nbsp;时间:" + DateUtil.format(new Date(), "yyyyMMdd HH:mm:ss") + "<br/>" + ExceptionUtils.getStackTrace(ex);
                String title = appUrls.get(key) + "===系统异常日志";

                /*BeanManager.getBean(MqSendCore.class)
                        .send(BeanManager.getBean(MailMessageHandle.class), MailMessageHandle.createSysMail(title, content, new ArrayList<String>(SysManagerService.mails)));*/
            }
        }
    }

    /**
     * mq 异常消息通知
     *
     * @param msgId 消息编号
     * @param commandName 消息处理的command名称
     * @param ex 异常信息
     * @param sys 系统
     */
    public void saveMqExceptionLog(String msgId, String commandName, Exception ex, int sys) {

        SysErrorLog log = new SysErrorLog();
        log.setException(ExceptionUtils.getStackTrace(ex).substring(0, 500));
        log.setMsgId(msgId);

        log.setSys(sys);
        log.setAddTime(new Date());

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        sysManagerDao.saveSysErrorLog(log);

        if (Constants.SERVER_NAME_MQ_SERVER.equals(sys)) {// TODO 是否需要短信提醒待定
            String content = "Push:---时间:" + DateUtil.format(new Date(), "yyyyMMdd HH:mm:ss") + "<br/>" + ExceptionUtils.getStackTrace(ex);
            String title = commandName + "===系统异常日志";

            /*BeanManager.getBean(MqSendCore.class)
                    .send(BeanManager.getBean(MailMessageHandle.class), MailMessageHandle.createSysMail(title, content, new ArrayList<String>(SysManagerService.mails)));*/
        }
    }

    /**
     * TODO 定时任务只记录异常日志，不发送邮件，避免大量发送邮件
     * 定时器异常监控以及通知
     *
     * @param taskId 任务编号
     * @param ex 异常信息
     * @param sys 系统
     */
    public void saveSchedulerExceptionLog(Long taskId, Exception ex, int sys, Long taskRecordId, Integer operationType) {

        SysErrorLog log = new SysErrorLog();
        log.setException(ExceptionUtils.getStackTrace(ex).substring(0, 500));

        log.setSys(sys);
        log.setAddTime(new Date());

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        sysManagerDao.saveSysErrorLog(log);

        if (Constants.SERVER_NAME_SCHEDULE.equals(sys)) {// app重要的接口异常短信推送/邮件推送 TODO 是否需要短信提醒待定
            String content = "scheduler:---时间:" + DateUtil.format(new Date(), "yyyyMMdd HH:mm:ss") + "<br/>" + ExceptionUtils.getStackTrace(ex);
            String title = "系统异常日志===任务编号:" + taskId;
            if (taskRecordId != null) {
                title += ", 操作编号:" + taskRecordId;
            }
            if (operationType != null) {
                title +=  ",操作类型:" + operationType;
            }

            /*BeanManager.getBean(MqSendCore.class)
                    .send(BeanManager.getBean(MailMessageHandle.class), MailMessageHandle.createSysMail(title, content, new ArrayList<String>(SysManagerService.mails)));*/
        }
    }

    /**
     * 服务器内部公共异常数据监控
     *
     * @param content 内容
     * @param other 相关参数 JSON字符串
     */
    public void saveCommonOversee(String url, String content, String other) {
        SysErrorLog log = new SysErrorLog();
        log.setException(content);
        log.setUserId(null);
        log.setSys(Constants.SERVER_COMMON);

        log.setRequestParams(other);
        log.setUa("");
        log.setIp("");
        log.setAddTime(new Date());

        log.setRequestUri(url);

        log.setSolveStatus(SysErrorLog.SOLVE_STATUS_NO);
        sysManagerDao.saveSysErrorLog(log);
    }

    /**
     * 系统接口异常 列表查询
     * @param page 分页对象
     */
    public void findErrorLogList(Page<SysErrorLog> page) {
        sysManagerDao.findSysErrorLog(page);
    }

    /**
     * 修改异常日志处理状态
     * @param solveStatus 新的状态
     * @param id 异常日志编号
     */
    public void modifySysErrorLog(Integer solveStatus, Integer id) {
        sysManagerDao.modifySysErrorLog(Update.update("solveStatus", solveStatus), id);
    }
}
