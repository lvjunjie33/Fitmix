package com.business.bbs.base.interceptor;

import com.business.core.constants.Constants;
import com.business.core.entity.user.User;
import com.business.core.utils.DateUtil;
import org.springframework.web.servlet.HandlerMapping;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 * Created by fenxio on 2016/8/24.
 * 登录拦截
 */
public class LoginInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        boolean result = false;
        // 暂时放在这
        Map<String, String> map = DateUtil.getCurrentTime();
        request.getSession().setAttribute("SYSTEMTIME", map);

//        User user = (User) request.getSession().getAttribute(Constants.CURRENT_USER);
//        if(null == user) {
//            response.sendRedirect("/ugc/admin/login");
//        } else {
//            //URI 参数
//            Map pathVariables = (Map) request.getAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE);
//            //只能修改当前登录用户信息
//            if(user.getId() != Long.parseLong( (String) pathVariables.get("uid"))) {
//                pathVariables.put("uid", user.getId()+"");
//                request.setAttribute(HandlerMapping.URI_TEMPLATE_VARIABLES_ATTRIBUTE, pathVariables);
//            }
//            result = true;
//        }
        return true;
    }
}
