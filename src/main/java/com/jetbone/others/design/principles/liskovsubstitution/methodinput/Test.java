package com.jetbone.others.design.principles.liskovsubstitution.methodinput;


import java.util.HashMap;

/**
 * Created by Chris on 2019-07-29 09:47.
 */
public class Test {
    public static void main(String[] args) {

        Child child = new Child();
        HashMap map = new HashMap();

        // 当子类重载的方法入参范围大于父类的时候
        // 我们可以传入一个范围较小的参数来调用被子类继承的父类方法
        child.method(map);

        // 当子类重载的方法入参范围小于父类
        // 除非我们把参数扩大，否则可能无法调用被继承的父类的方法
        // 并且也不符合子类对父类是一种扩展
        child.method1(map);
    }
}
