package com.business.app.base.context;

/**
 * User: sin
 * Date: 13-7-25
 * Time: 上午10:56
 */
public class SystemContextHolder {


    /**
     * 线程变量
     */
    private static ThreadLocal<SystemContext> userThread = new ThreadLocal<>();

    /**
     * 设置线程变量
     *
     * @param systemContext 线程变量
     */
    public static void put(SystemContext systemContext) {
        userThread.set(systemContext);
    }

    /**
     * @return 线程变量
     */
    public static SystemContext get() {
        return userThread.get();
    }

    /**
     * 清空线程变量
     */
    public static void remove() {
        userThread.remove();
    }
}