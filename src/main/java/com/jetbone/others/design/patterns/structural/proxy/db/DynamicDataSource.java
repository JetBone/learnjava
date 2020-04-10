package com.jetbone.others.design.patterns.structural.proxy.db;

import org.springframework.jdbc.datasource.lookup.AbstractRoutingDataSource;


/**
 * Created by Chris on 2019/8/31
 */
public class DynamicDataSource extends AbstractRoutingDataSource {

    /**
     * 返回值决定了当前线程使用哪个DB
     * @return
     */
    @Override
    protected Object determineCurrentLookupKey() {
        return DataSourceContextHolder.getDBType();
    }
}
