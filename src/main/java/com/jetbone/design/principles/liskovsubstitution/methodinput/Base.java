package com.jetbone.design.principles.liskovsubstitution.methodinput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019-07-29 09:44.
 */
public class Base {
    public void method(HashMap map) {
        System.out.println("父类HashMap方法被执行");
    }

    public void method1(Map map) {
        System.out.println("父类Map方法被执行");
    }
}
