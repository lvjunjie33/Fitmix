package com.business.core.annotation;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * 请注释文件作用
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MyBatisRepository {
}
