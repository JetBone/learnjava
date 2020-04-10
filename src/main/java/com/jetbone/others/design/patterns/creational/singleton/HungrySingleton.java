package com.jetbone.others.design.patterns.creational.singleton;

import java.io.Serializable;

/**
 * Created by Chris on 2019-08-11
 */
public class HungrySingleton implements Serializable {

    // 这里其实可以使用final修饰，也可以不实用final修饰，一切根据业务需求
    // 如果是懒加载，是不能加final的，
    // 单例模式中保存单例的变量都是static的，如果加上final，就成了final+static的组合
    // final+static的组合必然需要在类加载的时候完成初始化，那就必须成为饿汉式加载，而不是懒加载了
    private static HungrySingleton hungrySingleton = new HungrySingleton();

    // 标准的私有化构造器
    private HungrySingleton(){
        // 在私有化构造器里追加判断，可以防止使用反射再次创建新的实例
        // 这样的方式并不能完美的保证反射和获取的单例是同一个实例
        // 只能保证在获取实例的时候抛出异常，强行禁止破坏单例模式
        // 这样的判断方式在饿汉式单例模式中奏效，在懒汉式中会出现其他问题，详情请见LazySingleton构造器注释
        if (hungrySingleton != null) {
            throw new RuntimeException("单例模式禁止反射调用构造器");
        }
    }

    // 非常简单的一个饿汉式单例模式
    // 在类初始化的时候就进行了实例化，由JVM的类加载机制保证了单线程
    // 缺点就是初始化的时候就已经进行了实例化，如果之后没有用到该类，则造成了资源浪费
    public static HungrySingleton getInstance() {
        return hungrySingleton;
    }

    // 该方法并不是重载或者继承出来的方法，
    // 如果不去研究源码可能不知道需要增加这么一个方法来解决序列化和反序列化中的单例问题
    private Object readResolve() {
        return hungrySingleton;
    }
}
