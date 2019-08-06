package com.business.live.base.wxServlet;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.alibaba.fastjson.JSON;
import com.business.live.base.wxSHA1.SHA1;

public class WxUserInfoPage extends HandlerInterceptorAdapter {
	
	private final String APPID = "wx7adcfc1457f072b4";
	private final String AppSecret = "3022239db350cccc777bebc315a2dc51";
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	@Override
	/***
	 * afterCompletion在DispatcherServlet完全处理完请求后被调用,可用于清理资源等
	 */
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		// TODO Auto-generated method stub
		super.afterCompletion(request, response, handler, ex);
	}
	
	@Override
	/***
	 * postHandle在业务处理器处理请求执行完成后,生成视图之前执行
	 */
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
		// TODO Auto-generated method stub
		super.postHandle(request, response, handler, modelAndView);
	}
	
	@Override
	/***
	 * preHandle在业务处理器处理请求之前
	 */
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		logger.info("*************************************************************************");
		logger.info("测试拦截器");
		logger.info("*************************************************************************");
		request.getSession().setAttribute("userInfoStr", "{}");//设置默认userinfo保证前段页面中不会出现错误
		//URLEncoder.encode(s)
		String code = request.getParameter("code");
		logger.info("*************************************************************************");
		logger.info("code:"+code);
		logger.info("*************************************************************************");
		if(code!=null){
			//通过code换取网页授权access_token
			String access_tokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+AppSecret+"&code="+code+"&grant_type=authorization_code";
			 
			String refresh_token = (String) JSON.parseObject(SHA1.getHtml(access_tokenURL)).get("refresh_token");
			logger.info("*************************************************************************");
			logger.info("refresh_token:"+refresh_token);
			logger.info("*************************************************************************");
//			//刷新access_token 获取较长的授权
			
			String access_tokenURL_2 = SHA1.getHtml("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token="+refresh_token);
			logger.info("*************************************************************************");
			logger.info("access_tokenURL_2:"+access_tokenURL_2);
			logger.info("*************************************************************************");
//			
//			//拉取用户信息(需scope为 snsapi_userinfo)
			String access_token = (String)JSON.parseObject(access_tokenURL_2).get("access_token");//jsonPage(access_tokenURL_2, "access_token");
			String openid = (String)JSON.parseObject(access_tokenURL_2).get("openid");//jsonPage(access_tokenURL_2, "openid");
			
			String userInfo = SHA1.getHtml("https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
			logger.info("*************************************************************************");
			logger.info("userInfo:"+userInfo);
			logger.info("*************************************************************************");
			request.getSession().setAttribute("userInfoStr", userInfo);
			//return true;
		}
		//return false;
		return true;//测试的时候用的
	}

	
	
}
