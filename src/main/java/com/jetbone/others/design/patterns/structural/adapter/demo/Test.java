package com.jetbone.others.design.patterns.structural.adapter.demo;

/**
 * Created by Chris on 2019/8/27
 */
public class Test {

    public static void main(String[] args) {
        DC5 dc5  = new PowerAdapter();
        int output = dc5.outputDC5();
        System.out.println("output dc" + output);
    }
}
