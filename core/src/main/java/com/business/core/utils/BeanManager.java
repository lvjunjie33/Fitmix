package com.business.core.utils;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.List;

/**
 * Spring Manager
 */
public class BeanManager implements ApplicationContextAware, DisposableBean {

    private static ApplicationContext applicationContext = null;

    @Override
    public void setApplicationContext(ApplicationContext arg0)
            throws BeansException {
        applicationContext = arg0;
    }

    /**
     * 获取bean
     *
     * @param <T> 返回的实体bean类型
     * @param name bean的名称
     * @return spring容器中的bean对象
     */
    public static <T> T getBean(String name) {
        checkApplicationContext();
        return (T) applicationContext.getBean(name);
    }

    /**
     * 从静态变量ApplicationContext中取得Bean, 自动转型为所赋值对象的类型.
     * 如果有多个Bean符合Class, 取出第一个.
     */
    public static <T> T getBean(Class<T> requiredType) {
        checkApplicationContext();
        return applicationContext.getBean(requiredType);
    }

    /**
     * 通过接口名获取所有子类
     * @param clazz 接口类型
     * @param <T>
     * @return
     */
    public static <T> List<T> getBeansByInterface(Class<T> clazz) {
        List<T> results = new ArrayList<T>();
        String[] beanNames = applicationContext.getBeanDefinitionNames();
        if (beanNames.length == 0) return results;

        for (String beanName : beanNames) {
            Object o = getBean(beanName);
            Type[] types = o.getClass().getGenericInterfaces();
            if (types.length == 0) continue;
            for(Type t : types) {
                if (t.equals(clazz)) {
                    results.add((T)o);
                }
            }
        }
        return results;
    }

    /**
     * 检查ApplicationContext不为空.
     */
    private static void checkApplicationContext() {
        if (applicationContext == null) {
            throw new IllegalStateException("applicationContext未注入,请在applicationContext.xml中定义SpringContextHolder");
        }
    }

    @Override
    public void destroy() throws Exception {
        cleanApplicationContext();
    }

    /**
     * 清除applicationContext静态变量.
     */
    public static void cleanApplicationContext() {
        applicationContext = null;
    }


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
}
