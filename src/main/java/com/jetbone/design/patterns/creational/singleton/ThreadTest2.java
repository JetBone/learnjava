package com.jetbone.design.patterns.creational.singleton;


/**
 * Created by Chris on 2019-08-11 18:56.
 */
public class ThreadTest2 implements Runnable {
    @Override
    public void run() {
        LazyDoubleCheckSingleton lazyDoubleCheckSingleton = LazyDoubleCheckSingleton.getInstance();
        System.out.println(Thread.currentThread().getName() + ": " + lazyDoubleCheckSingleton);
    }
}
