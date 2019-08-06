package com.business.live.base.wxServlet;

import java.util.Timer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * 初始化微信普通权限认证
 * Servlet implementation class Authenticate
 */
@WebServlet("/Authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public void init() throws ServletException {
		logger.info("servlet 微信初始化完成......");

		Timer timer = new Timer();
		timer.schedule(new Tack(this.getServletContext()), 100,7200 * 1000);

	}
}
