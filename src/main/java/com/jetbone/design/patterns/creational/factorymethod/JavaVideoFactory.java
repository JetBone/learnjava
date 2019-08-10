package com.jetbone.design.patterns.creational.factorymethod;

/**
 * Created by Chris on 2019-08-10 12:27.
 */
public class JavaVideoFactory implements VideoFactory {
    @Override
    public Video getVideo() {
        return new JavaVideo();
    }
}
