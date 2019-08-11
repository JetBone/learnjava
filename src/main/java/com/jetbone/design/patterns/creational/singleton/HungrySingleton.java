package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11
 */
public class HungrySingleton {

    // 标准的私有化构造器
    private HungrySingleton(){
    }

    // 这里其实可以使用final修饰，也可以不实用final修饰，一切根据业务需求
    // 如果是懒加载，是不能加final的，
    // 单例模式中保存单例的变量都是static的，如果加上final，就成了final+static的组合
    // final+static的组合必然需要在类加载的时候完成初始化，那就必须成为饿汉式加载，而不是懒加载了
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    // 非常简单的一个饿汉式单例模式
    // 在类初始化的时候就进行了实例化，由JVM的类加载机制保证了单线程
    // 缺点就是初始化的时候就已经进行了实例化，如果之后没有用到该类，则造成了资源浪费
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }
}
