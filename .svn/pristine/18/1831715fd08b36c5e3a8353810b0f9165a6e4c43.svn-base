package com.business.core.utils;

import com.business.core.entity.joinActivity.JoinActivity;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Date;
import java.util.HashMap;
import java.util.UUID;

/**
 * Created by fenxio on 2016/7/7.
 */
public class ValidatedUtil {

    /**
     * 检验对象属性是否为空
     * @param bean
     * @return true 有空值 false 无空值
     */
    public static boolean checkFieldValueNull(Object bean) {
        boolean result = false;
        if (bean == null) {
            return true;
        }
        Class<?> cls = bean.getClass();
        Method[] methods = cls.getDeclaredMethods();
        Field[] fields = cls.getDeclaredFields();
        for (Field field : fields) {
            try {
                String fieldGetName = parGetName(field.getName());
                if (!checkGetMet(methods, fieldGetName)) {
                    continue;
                }
                Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                Object fieldVal = fieldGetMet.invoke(bean, new Object[]{});
                if (fieldVal != null) {
                    if(field.getType().toString().contains("String")) {
                        if ("".equals(fieldVal)) {
                            result = true;
                            break;
                        }
                    } else if(field.getType().toString().contains("HashMap")) {
                        HashMap<String, Object> map = (HashMap<String, Object>) fieldVal;
                        if(map.size() == 0) {
                            result = true;
                            break;
                        }
                    }

                } else {
                    result = true;
                    break;
                }
            } catch (Exception e) {
                continue;
            }
        }
        return result;
    }


    /**
     * 拼接某属性的 get方法
     *
     * @param fieldName
     * @return String
     */
    public static String parGetName(String fieldName) {
        if (null == fieldName || "".equals(fieldName)) {
            return null;
        }
        int startIndex = 0;
        if (fieldName.charAt(0) == '_')
            startIndex = 1;
        return "get"
                + fieldName.substring(startIndex, startIndex + 1).toUpperCase()
                + fieldName.substring(startIndex + 1);
    }

    /**
     * 判断是否存在某属性的 get方法
     *
     * @param methods
     * @param fieldGetMet
     * @return boolean
     */
    public static boolean checkGetMet(Method[] methods, String fieldGetMet) {
        for (Method met : methods) {
            if (fieldGetMet.equals(met.getName())) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args){
        JoinActivity joinActivity = new JoinActivity();
        HashMap<Object, Object> activityMoney = new HashMap<>();
        activityMoney.put("1",1);
        joinActivity.setAddTime(System.currentTimeMillis());
        joinActivity.setActivityBeginTime(System.currentTimeMillis());
        joinActivity.setActivityEndTime(System.currentTimeMillis());
        joinActivity.setSignUpBeginTime(System.currentTimeMillis());
        joinActivity.setSignUpEndTime(System.currentTimeMillis());
        joinActivity.setActivityId("11");
        joinActivity.setChannel(1);
        joinActivity.setThemeName("name");
        joinActivity.setThemeImage("image");
        joinActivity.setUrl("url");
//        joinActivity.setDesc("desc");
        joinActivity.setActivityMoney(activityMoney);
        System.out.println(UUID.randomUUID().toString());
        System.out.println(DateUtil.getDayBefore(new Date()).getTime());
    }

}
