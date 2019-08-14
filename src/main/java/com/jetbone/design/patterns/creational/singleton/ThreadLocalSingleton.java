package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-14
 */
public class ThreadLocalSingleton {

    /**
     * ThreadLocal会为每个线程单独维护一个变量
     * 所以使用ThreadLocal最终效果是，每个线程都是不同的实例，但是每个线程里是种保持单例
     */
    private static ThreadLocal<ThreadLocalSingleton> singleton = new ThreadLocal<ThreadLocalSingleton>() {
        @Override
        protected ThreadLocalSingleton initialValue() {
            return new ThreadLocalSingleton();
        }
    };

    private ThreadLocalSingleton() {
    }

    public static ThreadLocalSingleton getInstance() {
        return singleton.get();
    }

}
