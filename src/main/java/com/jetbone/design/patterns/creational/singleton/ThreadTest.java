package com.jetbone.design.patterns.creational.singleton;

/**
 * Created by Chris on 2019-08-11 17:17.
 */
public class ThreadTest implements Runnable {
    @Override
    public void run() {
        LazySingleton lazySingleton = LazySingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ": " + lazySingleton);
    }
}
