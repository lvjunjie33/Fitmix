package com.business.core.servlet;

import com.business.core.constants.ApplicationConfig;
import com.business.core.entity.SysParam;
import com.business.core.utils.DicUtil;
import com.business.core.utils.RedisUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

/**
 * 基础初始化Servlet。<br />
 * tip: 只调用几个项目必须开启的。剩余的通过实现abstract方法来初始化/停止
 * User: yunai
 * Date: 13-11-3
 * Time: 下午7:53
 */
public abstract class AbstractBaseInitServlet extends HttpServlet {

    protected Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    public void init() throws ServletException {
        // 子类实现
        try {
            //初始化配置
            ApplicationConfig.init();
            //redis init
            RedisUtil.init();
            //初始化 字典
            DicUtil.init();
            //初始化 系统参数
            SysParam.init();

            beforeInit();

            afterInit();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    @Override
    public void destroy() {
        try {
            //之前销毁
            beforeDestroy();
            //之后销毁
            afterDestroy();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    protected abstract void beforeInit() throws Exception;

    protected abstract void afterInit() throws Exception;

    protected abstract void beforeDestroy() throws Exception;

    protected abstract void afterDestroy() throws Exception;
}
