package com.business.work.base.shiro;

import com.business.core.entity.auth.Admin;
import com.business.core.utils.HttpUtil;
import com.google.common.collect.ImmutableSet;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.apache.shiro.web.servlet.AdviceFilter;
import org.apache.shiro.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;

/**
 * 记录用户访问日志
 * 
 * @author edward
 * @Description:
 */
public class VisitLogFilter extends AdviceFilter {

    private final Log logger = LogFactory.getLog(this.getClass());

    private final ImmutableSet<String> USER_NAME = new ImmutableSet.Builder<String>()
            .add("zhangtao")
            .add("admin")
            .add("sin")
            .add("jie")
            .add("xyq")
            .add("qudao")
            .add("cy")
            .add("xum")
            .add("weird")
            .add("wuqiong")
            .add("fanghuan")
            .add("wmg")
            .add("test")
            .add("qj")
            .add("mmm")
            .build();

    /**
     * 不记录访问日志的URI
     */
    private final ImmutableSet<String> URI_EXCLUDE_SET = new ImmutableSet.Builder<String>()
            .add("/user/outLoginAjax")
            .add("/user/outLogin")
            .build();
    
    @Override
	protected boolean preHandle(ServletRequest request, ServletResponse response)
			throws Exception {
		String uri = WebUtils.getPathWithinApplication((HttpServletRequest) request);

		HttpServletRequest req = WebUtils.toHttp(request);
//        logger.error("来访者:[IP-" + HttpUtil.getIP(req) + "],[UA-" + HttpUtil.getUA(req) + "],[uri-" + uri + "]");

//        Subject subject = SecurityUtils.getSubject();
//        if (subject != null) {
//            if (subject.isAuthenticated()) {
//                Admin admin = (Admin) subject.getPrincipal();
//                if (USER_NAME.contains(admin.getLoginName())) {
//                    String ip = HttpUtil.getIP(req);
//                    if (!(ip.contains("183.14") || ip.contains("192.168.1") || ip.contains("127.0.0.1"))) {
//                        logger.error("ip >> " + HttpUtil.getIP(req));
//                        return false;
//                    }
//                }
//            }
//        }
		// 检测访问者UA 是否合法
		return true;
	}
    
    @Override
    public void afterCompletion(ServletRequest request, ServletResponse response, Exception exception) throws Exception {
        HttpServletRequest req = WebUtils.toHttp(request);
        String uri = WebUtils.getPathWithinApplication(req);
        if (uri.indexOf(".htm") == 0) {
            return;
        }
        
        // 操作日志记录
 		/*if(req.getHeader("x-requested-with") == null){ // ajax请求去掉
 			String userId = null;
 			String ip = CommonUtils.getIpAddr(req);
 			if(SecurityUtils.getSubject().isAuthenticated()) {
 				userId = SessionInfo.getUserId();
 			}
 			SysLogBusiness record = new SysLogBusiness();
 			record.setUserId(userId);
 			record.setVisitIp(ip);
 			record.setVisitUrl(uri);
 			sysLogBusinessService.insert(record);
 		}*/
    }

}
