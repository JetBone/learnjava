package com.jetbone.others.design.principles.compositionaggregation;

/**
 * Created by Chris on 2019-07-30 21:34.
 */
public class NewDao {
    private IDBConnection connection;

    public void setConnection(IDBConnection connection) {
        this.connection = connection;
    }

    public void addInfo() {
        String connection = this.connection.getConnection();
        System.out.println("Use " + connection + " to add info.");
    }
}
