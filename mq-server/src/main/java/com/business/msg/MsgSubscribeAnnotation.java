package com.business.msg;

import org.springframework.stereotype.Component;

import java.lang.annotation.*;

/**
 * Created by edward on 2017/9/18.
 */
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Documented
@Component
public @interface MsgSubscribeAnnotation {

    String channel();
}
