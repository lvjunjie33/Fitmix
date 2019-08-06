package com.business.work.sys;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;
import com.business.core.entity.SysParam;
import com.business.core.utils.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.ReflectionUtils;

import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

/**
 * Created by Administrator on 2015/4/29.
 */
@Service
public class SysParamService {

    /**
     * 类型 - Int
     */
    private static final int TYPE_INT = 1;
    /**
     * 类型 - Short
     */
    private static final int TYPE_SHORT = 2;
    /**
     * 类型 - Long
     */
    private static final int TYPE_LONG = 3;
    /**
     * 类型 - Double
     */
    private static final int TYPE_DOUBLE = 4;
    /**
     * 类型 - Float
     */
    private static final int TYPE_FLOAT = 5;
    /**
     * 类型 - String
     */
    private static final int TYPE_STRING = 6;
    /**
     * 类型 - Boolean
     */
    private static final int TYPE_BOOLEAN = 7;
    /**
     * 类型 - Date
     */
    private static final int TYPE_DATE = 8;
    /**
     * 类型 - String数组
     */
    private static final int TYPE_ARRAY_STRING = 9;
    /**
     * 类型 - Map<String, String>
     */
    private static final int TYPE_MAP_STRING_STRING = 10;
    /**
     * 类型 - Map<String, Boolean>
     */
    private static final int TYPE_MAP_STRING_BOOLEAN = 11;
    /**
     * 类型 - Map<String, Integer>
     */
    private static final int TYPE_MAP_STRING_INTEGER = 12;
    /**
     * 类型 - Map<String, Object>
     */
    private static final int TYPE_MAP_STRING_OBJECT = 13;
    /**
     * 类型 - List<String>
     */
    private static final int TYPE_LIST_STRING = 14;
    /**
     * 类型 - List<Integer>
     */
    private static final int TYPE_LIST_INTEGER = 15;
    /**
     * 类型 - List<Map<String, Object>>
     */
    private static final int TYPE_LIST_MAP_STRING_OBJECT = 16;
    /**
     * 类型 - List<Map<String, String>>
     */
    private static final int TYPE_LIST_MAP_STRING_STRING = 17;
    /**
     * 类型 - List<Object[]>
     */
    private static final int TYPE_LIST_ARRAY_OBJECT = 18;
    /**
     * 类型 - Set<Integer>
     */
    private static final int TYPE_SET_INTEGER = 19;
    /**
     * 类型 - Set<String>
     */
    private static final int TYPE_SET_STRING = 20;
    /**
     * 类型 - List<Long>
     */
    private static final int TYPE_LIST_LONG = 21;


    @Autowired
    private SysParamDao sysParamDao;

    public List<Map<String, Object>> sysParamList () {
        SysParam param = sysParamDao.findSysParam();
        final SysParam entity = param == null ? new SysParam() : param;
        final List<Map<String, Object>> list = new ArrayList<>();
        ReflectionUtils.doWithFields(SysParam.class, new ReflectionUtils.FieldCallback() {
            @Override
            public void doWith(Field field) throws IllegalArgumentException, IllegalAccessException {
                if (field.getModifiers() != Modifier.PUBLIC) {
                    return;
                }
                list.add(parseField(entity, field));
            }
        });
        return list;
    }

    public void modify(String name, String val) {
        try {
            SysParam entity = sysParamDao.findSysParam();
            if (entity == null) {
                entity = new SysParam();
            }
            Field field = ReflectionUtils.findField(entity.getClass(), name);
            Object oldValue = field.get(entity);
            Map<String, Object> row = parseField(entity, field);
            int type = (Integer) row.get("type");
            Object value = null;
            switch (type) {
                case TYPE_INT:
                    value = Integer.valueOf(val);
                    break;
                case TYPE_SHORT:
                    value = Short.valueOf(val);
                    break;
                case TYPE_LONG:
                    value = Long.valueOf(val);
                    break;
                case TYPE_DOUBLE:
                    value = Double.valueOf(val);
                    break;
                case TYPE_FLOAT:
                    value = Float.valueOf(val);
                    break;
                case TYPE_STRING:
                    value = val;
                    break;
                case TYPE_BOOLEAN:
                    value = Boolean.valueOf(val);
                    break;
                case TYPE_DATE:
                    value = DateUtil.parse(val, "yyyy/MM/dd HH:mm:ss");
                    break;
                case TYPE_ARRAY_STRING:
                    List<String> strings = JSON.parseArray(val, String.class);
                    value = strings.toArray(new String[strings.size()]);
                    break;
                case TYPE_MAP_STRING_STRING:
                    value = JSON.parseObject(val, new TypeReference<Map<String, String>>() {
                    });
                    break;
                case TYPE_MAP_STRING_BOOLEAN:
                    value = JSON.parseObject(val, new TypeReference<Map<String, Boolean>>() {
                    });
                    break;
                case TYPE_MAP_STRING_INTEGER:
                    value = JSON.parseObject(val, new TypeReference<Map<String, Integer>>() {
                    });
                    break;
                case TYPE_MAP_STRING_OBJECT:
                    value = JSON.parseObject(val, new TypeReference<Map<String, Object>>() {
                    });
                    break;
                case TYPE_LIST_STRING:
                    value = JSON.parseObject(val, new TypeReference<List<String>>() {
                    });
                    break;
                case TYPE_LIST_INTEGER:
                    value = JSON.parseObject(val, new TypeReference<List<Integer>>() {
                    });
                    break;
                case TYPE_LIST_MAP_STRING_OBJECT:
                    value = JSON.parseObject(val, new TypeReference<List<Map<String, Object>>>() {
                    });
                    break;
                case TYPE_LIST_MAP_STRING_STRING:
                    value = JSON.parseObject(val, new TypeReference<List<Map<String, String>>>() {
                    });
                    break;
                case TYPE_LIST_ARRAY_OBJECT:
                    value = JSON.parseObject(val, new TypeReference<List<Object[]>>() {
                    });
                    break;
                case TYPE_SET_INTEGER:
                    value = JSON.parseObject(val, new TypeReference<Set<Integer>>() {
                    });
                    break;
                case TYPE_SET_STRING:
                    value = JSON.parseObject(val, new TypeReference<Set<String>>() {
                    });
                    break;
                case TYPE_LIST_LONG:
                    value = JSON.parseObject(val, new TypeReference<List<Long>>() {
                    });
                    break;
            }
            Assert.notNull(value);
            field.set(entity, value);

            // 进行数据库更新
            Update update = new Update();
            switch (type) {
                case TYPE_MAP_STRING_STRING:
                    update.set(name, value);
                    break;
                default:
                    update.set(name, value);
            }
            sysParamDao.updateSysParam(update);
            // 重新刷新缓存
            SysParam.init();
        } catch (Exception e) {
             e.printStackTrace();
        }
    }

    private Map<String, Object> parseField(SysParam entity, Field field) throws IllegalAccessException {
        if (field.isAccessible()) {
            field.setAccessible(true);
        }
        Map<String, Object> row = new HashMap<>();
        row.put("name", field.getName());
        row.put("val", field.get(entity));
        int type = 0;
        if (field.getType() == int.class
                || field.getType() == Integer.class) {
            type = TYPE_INT;
        } else if (field.getType() == short.class
                || field.getType() == Short.class) {
            type = TYPE_SHORT;
        } else if (field.getType() == long.class
                || field.getType() == Long.class) {
            type = TYPE_LONG;
        } else if (field.getType() == double.class
                || field.getType() == Double.class) {
            type = TYPE_DOUBLE;
        } else if (field.getType() == float.class
                || field.getType() == Float.class) {
            type = TYPE_FLOAT;
        } else if (field.getType() == String.class) {
            type = TYPE_STRING;
        } else if (field.getType() == boolean.class
                || field.getType() == Boolean.class) {
            type = TYPE_BOOLEAN;
        } else if (field.getType() == Date.class) {
            type = TYPE_DATE;
        } else if (field.getType() == String[].class) {
            type = TYPE_ARRAY_STRING;
        } else if ("java.util.Map<java.lang.String, java.lang.String>".equals(field.getGenericType().toString())) {
            type = TYPE_MAP_STRING_STRING;
        } else if ("java.util.Map<java.lang.String, java.lang.Boolean>".equals(field.getGenericType().toString())) {
            type = TYPE_MAP_STRING_BOOLEAN;
        } else if ("java.util.Map<java.lang.String, java.lang.Integer>".equals(field.getGenericType().toString())) {
            type = TYPE_MAP_STRING_INTEGER;
        } else if ("java.util.Map<java.lang.String, java.lang.Object>".equals(field.getGenericType().toString())) {
            type = TYPE_MAP_STRING_OBJECT;
        } else if ("java.util.List<java.lang.String>".equals(field.getGenericType().toString())) {
            type = TYPE_LIST_STRING;
        } else if ("java.util.List<java.lang.Integer>".equals(field.getGenericType().toString())) {
            type = TYPE_LIST_INTEGER;
        } else if ("java.util.List<java.util.Map<java.lang.String, java.lang.Object>>".equals(field.getGenericType().toString())) {
            type = TYPE_LIST_MAP_STRING_OBJECT;
        } else if ("java.util.List<java.util.Map<java.lang.String, java.lang.String>>".equals(field.getGenericType().toString())) {
            type = TYPE_LIST_MAP_STRING_STRING;
        } else if ("java.util.List<[Ljava.lang.Object;>".equals(field.getGenericType().toString())) {
            type = TYPE_LIST_ARRAY_OBJECT;
        } else if ("java.util.Set<java.lang.Integer>".equals(field.getGenericType().toString())) {
            type = TYPE_SET_INTEGER;
        } else if ("java.util.Set<java.lang.String>".equals(field.getGenericType().toString())) {
            type = TYPE_SET_STRING;
        } else if ("java.util.List<java.lang.Long>".equals(field.getGenericType().toString())) {
            type = TYPE_LIST_LONG;
        }
        row.put("type", type);
        SysParam.Description description = field.getAnnotation(SysParam.Description.class);
        if (description != null) {
            row.put("module", description.module());
            row.put("desc", description.desc());
        }
        return row;
    }


}
