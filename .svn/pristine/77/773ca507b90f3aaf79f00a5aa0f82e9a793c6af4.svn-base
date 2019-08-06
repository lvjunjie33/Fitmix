package com.business.live.base.wxServlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.business.live.base.wxSHA1.SHA1;

/**
 * 微信获取用户信息授权
 * Servlet implementation class WxUserInfo
 */
@WebServlet("/WxUserInfo")
public class WxUserInfo extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	private final String APPID = "wx7adcfc1457f072b4";
	private final String AppSecret = "3022239db350cccc777bebc315a2dc51";
	
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public WxUserInfo() {
        // TODO Auto-generated constructor stub
    }
    
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		//URLEncoder.encode(s)
		String code = request.getParameter("code");
		logger.info("code ----------------------》》》》》》》》》》》》》 : "+code);
		logger.info("code ----------------------》》》》》》》》》》》》》 : "+request.getParameter("uid"));
		logger.info("code ----------------------》》》》》》》》》》》》》 : "+request.getParameter("mid"));
		logger.info("code ----------------------》》》》》》》》》》》》》 : "+request.getParameter("mo"));
		if(code!=null){
			//通过code换取网页授权access_token
			String access_tokenURL = "https://api.weixin.qq.com/sns/oauth2/access_token?appid="+APPID+"&secret="+AppSecret+"&code="+code+"&grant_type=authorization_code";
			
			String refresh_token = JSON.parseObject(SHA1.getHtml(access_tokenURL)).getString("refresh_token");
			logger.info("refresh_token : "+refresh_token);
			
			
//			//刷新access_token 获取较长的授权
			
			String access_tokenURL_2 = SHA1.getHtml("https://api.weixin.qq.com/sns/oauth2/refresh_token?appid="+APPID+"&grant_type=refresh_token&refresh_token="+refresh_token);
//			
//			//拉取用户信息(需scope为 snsapi_userinfo)
			String access_token = JSON.parseObject(SHA1.getHtml(access_tokenURL_2)).getString("access_token");//jsonPage(access_tokenURL_2, "access_token");
			String openid = JSON.parseObject(SHA1.getHtml(access_tokenURL_2)).getString("openid");//jsonPage(access_tokenURL_2, "openid");
			logger.info("access_token : "+access_token);
			logger.info("openid : " + openid);
			
			String userInfo = SHA1.getHtml("https://api.weixin.qq.com/sns/userinfo?access_token="+access_token+"&openid="+openid+"&lang=zh_CN");
			userInfo = userInfo == "" ? null : userInfo;
			logger.debug("wx -----> userInfo = {}",userInfo);
			request.getSession().setAttribute("userInfoStr", userInfo);

			logger.info("session userInfoStr : " + request.getSession().getAttribute("userInfoStr"));
		}
		request.getRequestDispatcher("/live.htm?uid=28&mid=11&mo=1&from=singlemessage&isappinstalled=1").forward(request, response);//转发
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
