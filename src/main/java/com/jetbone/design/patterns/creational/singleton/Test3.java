package com.jetbone.design.patterns.creational.singleton;

import java.lang.reflect.Constructor;

/**
 * Created by Chris on 2019-08-13
 */
public class Test3 {
    public static void main(String[] args) throws Exception {

        // 获取class类型
        Class clazz = HungrySingleton.class;
        // 第二种获取class的方式
//        Class clazz2 = Class.forName(HungrySingleton.class.getName());

        Constructor constructor = clazz.getDeclaredConstructor();
        // 这里需要注意由于我们声明的构造方法是private，所以直接调用会报错异常
        // 所以这里需要setAccessible(true)打开private权限，才能进行newInstance()调用
        constructor.setAccessible(true);
        HungrySingleton newInstance = (HungrySingleton) constructor.newInstance();

        // 获取单例
        HungrySingleton instance = HungrySingleton.getInstance();

        System.out.println(instance);
        System.out.println(newInstance);
        System.out.println(instance == newInstance);

        // 为了防止使用反射破坏单例模式
        // 可以在单例类的私有构造方法中增加一层判断
        // 加载类的时即创建单例(饿汉式)与调用时创建单例(懒汉式)必然会产生不同的效果
        // 饿汉式在加载类的时候，就已经给内部存放单例模式的参数赋值，而懒汉式只有调用的时候才会赋值
        // 懒汉式给了反射一个可以中途插一脚的机会
        // 详情请看LazySingleton和HungrySingleton构造器注释


    }
}
