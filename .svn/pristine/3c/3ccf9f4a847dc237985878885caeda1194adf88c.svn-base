package com.business.app.base.interceptor;

import com.business.app.base.context.SystemContext;
import com.business.app.base.context.SystemContextHolder;
import com.business.app.requestLog.RequestLogDao;
import com.business.core.entity.SysParam;
import com.business.core.entity.logs.RequestLog;
import com.business.core.entity.user.User;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Created by Sin on 2016/4/20.
 */
public class AppRequestInterceptor extends HandlerInterceptorAdapter {

    private RequestLogDao requestLogDao;

    AppRequestInterceptor(RequestLogDao requestLogDao) {
        this.requestLogDao = requestLogDao;
    }

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {

        SystemContext context = SystemContextHolder.get();

        return true;
    }


    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {

        try {
            SystemContext context = SystemContextHolder.get();
            RequestLog requestLog = new RequestLog();
            Long currentTime = System.currentTimeMillis();

            requestLog.setMatchedPath(context.getMatchedPath());
            requestLog.setUdid(context.getUdid());
            requestLog.setVersion(context.getVersion());
            requestLog.setTerminal(context.getTerminal());
            requestLog.setChannel(context.getChannel());
            requestLog.setNetwork(context.getNetwork());
            requestLog.setSdk(context.getSdk());
            requestLog.setDickValue(context.getDickValue());

            requestLog.setUid(context.getUid());
            //将日志信息保存用户信息改为保存用户编号信息

            requestLog.setIp(context.getIp());
            requestLog.setDoLogin(false);
            requestLog.setClientRequestTime(context.getClientRequestTime());
            requestLog.setServerReceiveTime(context.getServerReceiveTime());
            requestLog.setLanguage(context.getLanguage());

            // 计算 耗时
            requestLog.setServerResponseTime(currentTime);
            requestLog.setConsumeTime(currentTime - context.getServerReceiveTime());
            requestLog.setAddTime(currentTime);

            requestLogDao.insertRequestLog(requestLog);
        } catch (Exception e) {
            // skip
        }
    }
}
