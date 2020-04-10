package com.jetbone.others.design.patterns.structural.adapter;

/**
 * Created by Chris on 2019/8/27
 */
public class ConcreteTarget implements Target {
    @Override
    public void request() {
        System.out.println("concrete target request");
    }
}
