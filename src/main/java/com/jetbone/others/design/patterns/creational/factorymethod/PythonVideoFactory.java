package com.jetbone.others.design.patterns.creational.factorymethod;

/**
 * Created by Chris on 2019-08-10 12:28.
 */
public class PythonVideoFactory implements VideoFactory {
    @Override
    public Video getVideo() {
        return new PythonVideo();
    }
}
