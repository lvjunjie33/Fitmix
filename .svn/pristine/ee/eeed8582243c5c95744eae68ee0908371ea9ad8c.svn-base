package com.business.bbs.base.util;

import com.business.bbs.base.QxMap;
import com.business.core.entity.TaoBaoIp;
import com.business.core.entity.user.User;
import org.springframework.data.mongodb.core.query.Update;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;

/**
 * Created by fenxio on 2016/8/25.
 * 反射工具类
 */
public class ReflectionUtil {

    /**
     * 根据属性获取 bean 指定列的值
     * @param bean
     * @param fieldName
     * @return
     */
    public static Object getValueByFieldName(Object bean, String fieldName) {
        if (bean == null) {
            return null;
        }
        Object fieldVal = null;
        Class<?> cls = bean.getClass();
        try {
            String fieldGetName = parGetName(fieldName);
            Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
            fieldVal = fieldGetMet.invoke(bean, new Object[]{});
        } catch (Exception e) {

        }
        return fieldVal;
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

    /**
     * 获取 更新信息
     * @param record
     * @return
     */
    public static Update getUpdateInfo(Object record) {
        Update update = new Update();
        if (null != record) {
            Class<?> cls = record.getClass();
            Method[] methods = cls.getDeclaredMethods();
            Field[] fields = cls.getDeclaredFields();
            for (Field field : fields) {
                try {
                    String fieldGetName = parGetName(field.getName());
                    if (!checkGetMet(methods, fieldGetName) || field.getName().equals("id")) {
                        continue;
                    }
                    Method fieldGetMet = cls.getMethod(fieldGetName, new Class[]{});
                    Object fieldVal = fieldGetMet.invoke(record, new Object[]{});
                    if (fieldVal != null) {
                        if(isBaseType(field.getType().toString())) {
                            update.set(field.getName(), fieldVal);
                        } else if(field.getType().toString().contains("HashMap") || field.getType().toString().contains("Map")) {
                            HashMap<String, Object> map = (HashMap<String, Object>) fieldVal;
                            for(String key : map.keySet()) {
                                update.set(field.getName()+"."+key, map.get(key));
                            }
                        } else {
                            HashMap<String, Object> map = beanToMap(fieldVal);
                            for(String key : map.keySet()) {
                                update.set(field.getName()+"."+key, map.get(key));
                            }
                        }
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }

        return update;
    }

    /**
     * 对象转成MAP
     * @param bean
     * @return
     */
    public static HashMap<String,Object> beanToMap(Object bean) {
        HashMap<String, Object> map = new HashMap<>();
        if(null != bean) {
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
                        map.put(field.getName(), fieldVal);
                    }
                } catch (Exception e) {
                    continue;
                }
            }
        }
        return map;
    }

    public static boolean isBaseType(String clazz) {
        boolean result = false;
        clazz = clazz.split(" ")[1];
        if(clazz.contains("String") || clazz.contains("Integer") || clazz.contains("Double")
                || clazz.contains("Float") || clazz.contains("Long") || clazz.contains("Short")
                || clazz.contains("int") || clazz.contains("float") || clazz.contains("double")
                || clazz.contains("long") || clazz.contains("short")) {
            result = true;
        }
        return result;
    }

    public static void main(String[] arg0) {
        System.out.println("RUN_LEVEL_8".substring(10));
        if(true) {
            return;
        }
        User user = new User();
        user.setId(1);
        user.setGender(2);
        user.setSignature("哈哈");
        user.setBirthday(new QxMap<String, String>().add("year", "1992").add("month", "10").add("day", "12"));
        TaoBaoIp taoBaoIp = new TaoBaoIp();
        taoBaoIp.setCountry("中国");
        taoBaoIp.setCity("深圳");
        user.setRegisterTaoBaoIp(taoBaoIp);
        Update update = ReflectionUtil.getUpdateInfo(user);
        System.out.print(ReflectionUtil.getValueByFieldName(user, "id1"));
    }

}
