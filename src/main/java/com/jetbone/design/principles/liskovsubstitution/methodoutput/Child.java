package com.jetbone.design.principles.liskovsubstitution.methodoutput;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by Chris on 2019-07-29 10:00.
 */
public class Child extends Base {

    /**
     * 子类实现/继承的方法返回值可以比父类范围小或相当
     * @return HashMap
     */
    @Override
    public HashMap method() {
        HashMap map = new HashMap();
        return map;
    }

//    /**
//     * 如果子类实现/继承的方法的返回值比父类大，会直接报错
//     * @return
//     */
//    @Override
//    public Object method() {
//        return null;
//    }
}
