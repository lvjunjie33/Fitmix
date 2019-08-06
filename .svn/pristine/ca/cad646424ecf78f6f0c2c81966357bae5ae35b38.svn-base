package com.business.core.mysql;

/**
 * Created by sin on 2015/10/28.
 */
public class DBContextHolder {

    /**
     * 线程绑定池
     */
    private static InheritableThreadLocal<String> DB_HOLDER = new InheritableThreadLocal<>();

    public static void setDbType(String dbType) {
        DB_HOLDER.set(dbType);
    }

    public static String getDbType() {
        return DB_HOLDER.get();
    }
}
