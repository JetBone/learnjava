package com.jetbone.others.design.principles.compositionaggregation;

/**
 * Created by Chris on 2019-07-30 21:33.
 */
public class OracleConnection implements IDBConnection {
    @Override
    public String getConnection() {
        return "Oracle Connection";
    }
}
