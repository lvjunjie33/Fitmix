package com.business.core.utils;

import java.lang.reflect.Method;
import java.util.*;

import org.springframework.beans.factory.FactoryBean;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;

import com.google.common.collect.ImmutableMap;

/**
 * Created by edward on 2016/6/17.
 * 接口扫描，并解析
 */
public class InterfaceScanUtil {

    public static abstract class AnnotationHandler {
        public abstract Object handler(Class<?> clazz);
    }

    /**
     * 获取注解中定义的过滤规则
     * @return
     */
    public static Set<Object> getFilterChainDefinitionByAnnotation(AnnotationHandler handler, String...strs){
        Set<Object> objects = new HashSet<>();
        List<Class<?>> cs = ClassUtil.getClasses(strs);
        for (Class<?> clazz : cs) {
            objects.add(handler.handler(clazz));
        }
        return objects;
    }
}
