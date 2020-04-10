package com.jetbone.others.design.principles.dependenceinversion;

/**
 * Created by Chris on 2019-07-17 21:45.
 */
public class StudyPython implements Study {
    @Override
    public void study() {
        System.out.println("Study Python");
    }
}
