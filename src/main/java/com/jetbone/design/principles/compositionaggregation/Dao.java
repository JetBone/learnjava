package com.jetbone.design.principles.compositionaggregation;

/**
 * Created by Chris on 2019-07-30 21:29.
 */
public class Dao extends DBConnection {

    public void addInfo() {
        String connection  = super.getConnection();
        System.out.println("Use " + connection + " to add info");
    }
}
