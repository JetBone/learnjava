package com.jetbone.design.principles.dependenceinversion;

/**
 * Created by Chris on 2019-07-17 21:44.
 */
public class StudyJavaScript implements Study {

    @Override
    public void study() {
        System.out.println("Study JavaScript");
    }
}
