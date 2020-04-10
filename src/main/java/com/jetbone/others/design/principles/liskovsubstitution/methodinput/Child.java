package com.jetbone.others.design.principles.liskovsubstitution.methodinput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019-07-29 09:45.
 */
public class Child extends Base {

//    /**
//     * 该方法只是一个重写
//     * @param map
//     */
//    @Override
//    public void method(HashMap map) {
//        System.out.println("子类HashMap被执行");
//    }

    public void method(Map map) {
        System.out.println("子类Map方法被执行");
    }

    public void method1(HashMap map) {
        System.out.println("子类Map方法被执行");
    }
}
