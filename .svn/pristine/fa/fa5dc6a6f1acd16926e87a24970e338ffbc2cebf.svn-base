package com.business.live.base.wxServlet;

import java.util.Date;
import java.util.TimerTask;

import javax.servlet.ServletContext;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.business.live.base.wxSHA1.SHA1;
/***
 * 定时获取微信基本授权
 * @author wjcaozhi
 *
 */
public class Tack extends TimerTask {
	
	private ServletContext servletContext;
	
	protected Logger logger = LoggerFactory.getLogger(getClass());
	
	public Tack(ServletContext servletContext) {
		this.servletContext = servletContext;
	}

	@Override
	public void run() {
		
		logger.info("定时而任务");
		
 		SHA1.setTime(System.currentTimeMillis());// 更新认证时间
		
 		String access_token = (String)JSON.parseObject(SHA1.getHtml(SHA1.WXURL+"?"+SHA1.WXPAGE)).get("access_token");

		String jsapi_ticket = SHA1.getHtml("https://api.weixin.qq.com/cgi-bin/ticket/getticket?access_token=" + access_token + "&type=jsapi");
		
		servletContext.setAttribute("wx_pt_access_token", "\""+access_token+"\"");
		servletContext.setAttribute("jsapiTicket", jsapi_ticket);
		
		logger.info("定时的wx_pt_access_token == " + access_token);
		logger.info("定时的jsapiTicket == " + jsapi_ticket);
		logger.info("定时 微信初始化完成......");
	}

}
