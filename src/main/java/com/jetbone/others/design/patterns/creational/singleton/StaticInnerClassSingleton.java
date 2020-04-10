package com.jetbone.others.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11 22:39.
 */
public class StaticInnerClassSingleton {

    // 私有的构造方法
    // 单例模式的基本
    private StaticInnerClassSingleton() {
    }

    // 内部类首先是static来保证调用
    // 然后是private，保证只有自己能调用
    // 内部成员变量也是static
    private static class InnerClass {
        private static StaticInnerClassSingleton staticInnerClassSingleton = new StaticInnerClassSingleton();
    }

    // 一个对外开放的获取实例的方法
    // 静态内部类实现单例模式的原理是
    // JVM在初始化一个Class的时候，线程会获取Class对象的初始化锁，保证一个Class只初始化一次(Class只有一个)
    // 在初始化一个Class的时候，也会初始化内部的所有静态成员
    // 这里是利用JVM加载Class的原理实现了单例模式，由JVM保证了单线程创建
    public static StaticInnerClassSingleton getInstance() {
        return InnerClass.staticInnerClassSingleton;
    }
}
