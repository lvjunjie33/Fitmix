package com.business.core.utils;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.google.common.collect.Sets;
import org.springframework.util.ReflectionUtils;
import org.springframework.util.StringUtils;

import java.lang.reflect.Field;
import java.util.*;

/**
 * 集合工具类
 */
public class CollectionUtil {

    public static List<String> splitList(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return new ArrayList<>(0);
        }
        String[] strs = str.split(regex);
        List<String> list = new ArrayList<>(strs.length);
        Collections.addAll(list, strs);
        return list;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static List<Integer> convertList(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        String[] strs = str.split(regex);
        List<Integer> list = new ArrayList<>(strs.length);
        for (String s : strs) {
            list.add(Integer.valueOf(s.trim()));
        }
        return list;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static Set<Integer> convertSet(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        String[] strs = str.split(regex);
        Set<Integer> set = Sets.newHashSetWithExpectedSize(strs.length);
        for (String s : strs) {
            set.add(Integer.valueOf(s));
        }
        return set;
    }
    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static Set<Long> convertSet2(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptySet();
        }
        String[] strs = str.split(regex);
        Set<Long> set = Sets.newHashSetWithExpectedSize(strs.length);
        for (String s : strs) {
            set.add(Long.valueOf(s));
        }
        return set;
    }

    /**
     * 将字符串转换为整数数组
     *
     * @param str   字符串
     * @param regex 字符串分隔符
     * @return 整数数组
     */
    public static List<Long> convertList2(String str, String regex) {
        if (StringUtils.isEmpty(str)) {
            return Collections.emptyList();
        }
        String[] strs = str.split(regex);
        List<Long> list = new ArrayList<Long>(strs.length);
        for (String s : strs) {
            list.add(Long.valueOf(s));
        }
        return list;
    }

    /**
     * 根据数组生成集合
     *
     * @param objs 集合数组
     * @param <T>  泛型
     * @return 集合
     */
    @SafeVarargs
    public static <T> Set<T> asSet(T... objs) {
        Set<T> set = Sets.newHashSetWithExpectedSize(objs.length);
        Collections.addAll(set, objs);
        return set;
    }

    @SafeVarargs
    public static <T> List<T> asArrayList(T... objs) {
        List<T> list = new ArrayList<T>(objs.length);
        Collections.addAll(list, objs);
        return list;
    }
    /**
     * 创建指定属性成为一个集合，比如说将一个患者信息数组建立出一个患者编号集合
     *
     * @param objs     数组
     * @param clazz    类型
     * @param property 属性名
     * @param <T>      泛型
     * @return 指定属性的集合
     */
    @SuppressWarnings("unchecked")
    public static <T> Set<T> buildSet(List<?> objs, Class<T> clazz, String property) {
        if (objs.isEmpty()) {
            return Sets.newHashSetWithExpectedSize(0);
        }
        Set<T> results = Sets.newHashSetWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (Object obj : objs) {
                T val = (T) field.get(obj);
                if (val == null) {
                    continue;
                }
                results.add(val);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建指定属性为KEY, objs的每个元素为值的Multimap的Map集合。
     *
     * @param objs     数组
     * @param keyClazz 值类型，即{@code property}的类型
     * @param valClazz 值类型
     * @param property 属性名
     * @param <K>      泛型
     * @param <V>      泛型
     * @return 指定属性的Map集合
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, List<V>> buildMultimap(List<V> objs, Class<K> keyClazz, Class<V> valClazz,
                                                       String property) {
        if (objs.isEmpty()) {
            return Maps.newHashMapWithExpectedSize(0);
        }
        Map<K, List<V>> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (V obj : objs) {
                K key = (K) field.get(obj);
                List<V> value = results.get(key);
                if (value == null) {
                    results.put(key, value = new ArrayList<V>());
                }
                value.add(obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建指定属性成为一个数组，比如说将一个患者信息数组建立出一个患者编号数组
     *
     * @param objs     数组
     * @param clazz    类型
     * @param property 属性名
     * @param <T>      泛型
     * @return 指定属性的数组
     */
    @SuppressWarnings("unchecked")
    public static <T> List<T> buildList(List<?> objs, Class<T> clazz, String property) {
        if (objs.isEmpty()) {
            return Lists.newArrayListWithExpectedSize(0);
        }
        List<T> results = new ArrayList<T>(objs.size());
        try {
            Field field = getField(objs, property);
            for (Object obj : objs) {
                T val = (T) field.get(obj);
                if (val == null) {
                    continue;
                }
                results.add(val);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建指定属性为KEY, objs的每个元素为值的Multimap的Map集合。
     *
     * @param objs 数组
     * @param keyClazz 值类型，即{@code property}的类型
     * @param valClazz 值类型
     * @param property 属性名
     * @param <K> 泛型
     * @param <V> 泛型
     * @return 指定属性的Map集合
     */
    @SuppressWarnings("unchecked")
    public static <K, V> Map<K, V> buildMap(List<V> objs, Class<K> keyClazz, Class<V> valClazz,
                                            String property) {
        if (objs.isEmpty()) {
            return Maps.newHashMapWithExpectedSize(0);
        }
        Map<K, V> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (V obj : objs) {
                K key = (K) field.get(obj);
                results.put(key, obj);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建数组里某个属性的次数
     *
     * @param objs 数组
     * @param keyClazz key的类
     * @param property key的属性名。当key对应的值为null是，不计数
     * @param <K> key的类
     * @return 次数集合
     */
    @SuppressWarnings("unchecked")
    public static <K> Map<K, Integer> buildCountMap(List<?> objs, Class<K> keyClazz, String property) {
        if (objs.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, Integer> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            for (Object obj : objs) {
                K val = (K) field.get(obj);
                if (val != null) {
                    incr(results, val);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    /**
     * 创建数组里某个属性为key，值属性为次数
     *
     * @param objs 数组
     * @param keyClazz key的类
     * @param property key的属性名。当key对应的值为null是，不计数
     * @param valProperty val的属性名
     * @param <K> key的类
     * @return 次数集合
     */
    @SuppressWarnings("unchecked")
    public static <K> Map<K, Integer> buildCountMap(List<?> objs, Class<K> keyClazz, String property, String valProperty) {
        if (objs.isEmpty()) {
            return Collections.emptyMap();
        }
        Map<K, Integer> results = Maps.newHashMapWithExpectedSize(objs.size());
        try {
            Field field = getField(objs, property);
            Field field2 = getField(objs, valProperty);
            for (Object obj : objs) {
                K val = (K) field.get(obj);
                Object val2 = field2.get(obj);
                if (val != null && val2 != null) {
                    incr(results, val, ((Number) val2).intValue());
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    public static Map<String, Number> buildPropertyCount(List<?> objs, String...propertys) {
        if (objs.isEmpty() || propertys.length == 0) {
            return Collections.emptyMap();
        }
        Map<String, Number> results = Maps.newHashMapWithExpectedSize(propertys.length);
        try {
            for (Object obj : objs) {
                for (int i = 0; i < propertys.length; i++) {
                    String property = propertys[i];
                    Field field = getField(objs, property);
                    Object val = field.get(obj);

                    Number resultVal = results.get(property) == null ? 0 : results.get(property);
                    if (val instanceof Integer) {
                        resultVal = resultVal.intValue() + (Integer)val;
                    }
                    else if (val instanceof Long) {
                        resultVal = resultVal.longValue() + (Long)val;
                    }
                    else if (val instanceof Double) {
                        resultVal = resultVal.doubleValue() + (Double)val;
                    }
                    results.put(property, resultVal);
                }
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return results;
    }

    @SuppressWarnings("unchecked")
    public static <V> V buildPropertyCount(List<?> objs, Class<V> keyClazz, String property) {
        if (objs.isEmpty() || property.isEmpty()) {
            return (V)new Object();
        }
        Double totalCount = 0.0;
        try {
            for (Object obj : objs) {
                Field field = getField(objs, property);
                Object valObj = field.get(obj);
                if (valObj == null) {
                    continue;
                }
                Double val = new Double(String.valueOf(field.get(obj)));
                totalCount += val;
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
        return (V)totalCount;
    }

    /**
     * @param objs     对象数组
     * @param property 属性
     * @return 对象元素里的指定属性Field, 并设置该field可以被访问
     */
    public static Field getField(List<?> objs, String property) {
        Field field = ReflectionUtils.findField(objs.get(0).getClass(), property);
        if (!field.isAccessible()) {
            field.setAccessible(true);
        }
        return field;
    }

    /**
     * 提升计数
     *
     * @param counts 计数集合
     * @param key key
     * @param <K> 泛型
     * @return 结果
     */
    public static <K> Integer incr(Map<K, Integer> counts, K key) {
        return incr(counts, key, 1);
    }

    public static <K> Integer incr(Map<K, Integer> counts, K key, int count) {
        Integer value = counts.get(key);
        if (value == null) {
            value = count;
        } else {
            value += count;
        }
        counts.put(key, value);
        return value;
    }
}