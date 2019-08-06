package com.business.core.mysql;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;

/**
 * Created by sin on 2015/10/28.
 * mysql 动态数据源
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    @Override
    protected Object determineCurrentLookupKey() {
        return DBContextHolder.getDbType();
    }
}
