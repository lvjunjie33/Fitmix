package com.business.app.base.excetion;

import com.alibaba.fastjson.JSON;
import com.business.core.constants.CodeConstants;
import com.business.core.constants.Constants;
import com.business.core.operations.sysManager.SysManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

public class MappingExceptionResolver extends SimpleMappingExceptionResolver {

	private Logger logger = LoggerFactory.getLogger(getClass());

	@Autowired
	private SysManagerService sysManagerService;
    
	@Override
	public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response,
			Object handler, Exception ex) {
		if (!(ex instanceof AppBusinessExcetion)) {
			logger.error("", ex);
			sysManagerService.saveInterfaceExceptionLog(request, response, ex, Constants.SERVER_NAME_APP);
		}
		String url = request.getRequestURI();
		if(url.contains(".json")) {
			Map<String, Object> em = new HashMap<>();

			if(ex instanceof AppBusinessExcetion) {
				AppBusinessExcetion appBusinessExcetion = (AppBusinessExcetion) ex;
				em.put("msg", appBusinessExcetion.getMessage());//消息码存入异常的消息中去读取
				em.put("code", appBusinessExcetion.getCode());
			} else {
				em.put("msg", "服务器异常");//消息码存入异常的消息中去读取
				em.put("code", CodeConstants.MISS);
			}
			try{
				String json = JSON.toJSONString(em);
				response.setCharacterEncoding("UTF-8");
				PrintWriter out = response.getWriter();
				out.println(json);
				out.flush();
				out.close();
			} catch (Exception e){
			}
			return new ModelAndView();

		}
		Map<String, String> map = new HashMap<>();
		return new ModelAndView("404.htm", map);
	}

	public SysManagerService getSysManagerService() {
		return sysManagerService;
	}

	public void setSysManagerService(SysManagerService sysManagerService) {
		this.sysManagerService = sysManagerService;
	}
}
