package com.business.core.utils;

import java.lang.reflect.Field;
import java.math.BigDecimal;
import java.util.List;
import java.util.Random;

/**
 * 数学工具类
 */
public class MathUtil {

    /**
     * 随机对象
     */
    public static final Random RANDOM = new Random();

    /**
     * @return 随即bool值
     */
    public static boolean nextBoolean() {
        return RANDOM.nextBoolean();
    }

    /**
     * 随即[0, n)范围内的值
     *
     * @param n 最大值
     * @return 整数
     */
    public static int nextInt(int n) {
        return RANDOM.nextInt(n);
    }

    /**
     * 除法。大值/小值
     *
     * @param a 值A
     * @param b 值B
     * @return 结果
     */
    public static long div(long a, long b) {
        return a > b ? a / b : b / a;
    }

    /**
     * 获得数组里的最小值
     *
     * @param array 整数数组
     * @return 最小值
     * @throws IllegalArgumentException 数组长度为1时
     */
    public static int min(int... array) {
        if (array.length == 0) {
            throw new IllegalArgumentException("array的长度不能为空！");
        }
        int min = array[0];
        for (int i = 1; i < array.length; i++) {
            min = Math.min(min, array[i]);
        }
        return min;
    }

    /**
     * 两数相减，若有一为null，则返回null
     *
     * @param a 被减数
     * @param b 减数
     * @return 结果
     */
    public static Integer subtract(Integer a, Integer b) {
        return a != null && b != null ? a - b : null;
    }

    /**
     * 两数相减，若有一为null，则返回null
     *
     * @param a 被减数
     * @param b 减数
     * @return 结果
     */
    public static Double subtract(Double a, Double b) {
        return a != null && b != null ? a - b : null;
    }

    public static int sum(List<?> objs, String fieldName) {
        Field field = CollectionUtil.getField(objs, fieldName);
        int sum = 0;
        for (Object obj : objs) {
            try {
                Integer val = (Integer) field.get(obj);
                if (val != null) {
                    sum += val;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return sum;
    }

    public static double sumDouble(List<?> objs, String fieldName) {
        Field field = CollectionUtil.getField(objs, fieldName);
        double sum = 0;
        for (Object obj : objs) {
            try {
                Double val = (Double) field.get(obj);
                if (val != null) {
                    sum += val;
                }
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        }
        return sum;
    }

    /**
     * 计算所需位置<br />
     * 即result = val % divideVal != 0 ? val / divideVal + 1 : val / divideVal;
     *
     * @param val       数量。当数量小于等于0时，返回0
     * @param divideVal 每个位置最大能装数量
     * @return 计算所需位置
     */
    public static long ceilX(long val, long divideVal) {
        return val <= 0 ? 0 : (val - 1 + divideVal) / divideVal;
    }

    /**
     * 计算所需位置<br />
     * 即result = val % divideVal != 0 ? val / divideVal + 1 : val / divideVal;
     *
     * @param val       数量。当数量小于等于0时，返回0
     * @param divideVal 每个位置最大能装数量
     * @return 计算所需位置
     */
    public static int ceilX(int val, int divideVal) {
        return val <= 0 ? 0 : (val - 1 + divideVal) / divideVal;
    }

    /**
     * 随机[min, max]范围内的数字
     *
     * @param min 随机开始
     * @param max 随机结束
     * @return 数字
     */
    public static int random(int min, int max) {
        if (min == max) {
            return min;
        }
        if (min > max) {
            int temp = min;
            min = max;
            max = temp;
        }
        // 随即开始
        int diff = max - min + 1;
        return RANDOM.nextInt(diff) + min;
    }

    /**
     * 随机[min, max]范围内的数字<br />
     * 实现的不太好，以后需要改，如果没必要，尽量不要调用该方法。
     *
     * @param min 随机开始
     * @param max 随机结束
     * @return 数字
     */
    public static long random(long min, long max) {
        if (min == max) {
            return min;
        }
        if (min > max) {
            long temp = min;
            min = max;
            max = temp;
        }
        // 随即开始
        long diff = max - min + 1;
        long x = RANDOM.nextLong();
        if (x < 0) {
            x = -x;
        }
        long temp = x % diff;
        return temp + min;
    }

    /**
     * 随机[0, max]范围内的数字
     *
     * @param max 随机结束
     * @return 数字
     */
    public static int random(int max) {
        return RANDOM.nextInt(max);
    }

    /**
     * 是否发生。
     * 比如说，发生概率为70%，则change(10, 7)
     *
     * @param a 发生概率
     * @param b 发生总概率
     * @return 是否发生
     */
    public static boolean chance(int a, int b) {
        return RANDOM.nextInt(a) < b;
    }


    /**
     * 浮点型保留指定位小数
     *
     * @param num 浮点数
     * @param scale 保留位数
     * @return
     */
    public static double setDoubleScale(double num, int scale) {
        return new BigDecimal(num).setScale(scale, BigDecimal.ROUND_HALF_UP).doubleValue();
    }

    /**
     * 浮点型保留2位小数
     *
     * @param num 浮点数
     * @return
     */
    public static double setDoubleScale(double num) {
        return setDoubleScale(num, 2);
    }

    public static double round (Double number, Integer position) {
        return Math.round(number * (Math.pow(10, position))) * 0.01d;
    }


    public static String randomStr = "0123456789";
    public static String randomStr(int len) {
        String str = "";
        for (int i = 0; i < len; i++) {
            int ran = MathUtil.random(randomStr.length() - 1);
            str += randomStr.charAt(ran);
        }
        return str;
    }
}

