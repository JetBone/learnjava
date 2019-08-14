package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11 17:17.
 */
public class ThreadTest implements Runnable {
    @Override
    public void run() {
        // 懒汉式单例模式，多线程测试
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ": " + lazySingleton);

        // 容器单例模式，多线程测试
        ContainerSingleton.setInstance("JetBone", new Object());
        Object containerSingleton = ContainerSingleton.getInstance("JetBone");
        System.out.println(Thread.currentThread().getName() + ": " + containerSingleton);

        // ThreadLocal单例模式，多线程测试
        ThreadLocalSingleton threadLocalSingleton = ThreadLocalSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ": " + threadLocalSingleton);
    }
}
