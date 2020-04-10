package com.jetbone.others.design.principles.compositionaggregation;

/**
 * Created by Chris on 2019-07-30 21:33.
 */
public class MysqlConnection implements IDBConnection {
    @Override
    public String getConnection() {
        return "Mysql Connection";
    }
}
