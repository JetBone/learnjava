package com.jetbone.design.patterns.creational.factorymethod;

/**
 * Created by Chris on 2019-08-01 22:51.
 */
public class PythonVideo implements Video {
    @Override
    public void produce() {
        System.out.println("Produce Python Video.");
    }
}